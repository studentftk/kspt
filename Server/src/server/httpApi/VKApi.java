package server.httpApi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.NetworkUtils;
import org.json.simple.parser.ParseException;
import server.DbConnectionFactory;
import server.logic.User;

/* simple test
https://oauth.vk.com/authorize?client_id=4601196&scope=offline&redirect_uri=https://studentspbstu.tk/vk/oauth&v=5.25&response_type=code
*/
public class VKApi implements HttpApiMethod {
    public final String contextURI = "/vk/oauth";
    public final String baseTokenQueryURI = "https://oauth.vk.com/access_token?client_id=4601196&client_secret=4FfKXAErEZYuC9G55RUK&v=5.25&redirect_uri="+NetworkUtils.getServerURL()+contextURI+"&code=";
    private final DbConnectionFactory dbConnectionfactory;

    public VKApi(DbConnectionFactory factory) {
        this.dbConnectionfactory = factory;
    }

    protected User getUserData(AuthInfo authInfo) throws IOException, ParseException {
        String query = "https://api.vk.com/method/users.get?fields=photo_50,first_name,last_name,universities,about&name_case=Nom&v=5.26&user_id="+authInfo.id+" ";
        URLConnection connection = new URL(query).openConnection();
        InputStream is = connection.getInputStream();
        JSONParser parser = new JSONParser();
        Map response  = (Map)parser.parse(new InputStreamReader(is, "UTF-8"));
        JSONArray responseValue = (JSONArray)response.get("response");
        Map<String,String> json = (Map<String,String>)responseValue.get(0);
        
        User user = new User();
        user.name = json.get("first_name");
        user.surname = json.get("last_name");
        user.photo = (String) json.get("photo_50");
        user.socialId = authInfo.id;
        user.socialType = "vk";
        user.socialToken = authInfo.token;
        return user;
    }

    @Override
    public HttpHandler getHandler() {
        return hadler;
    }

    @Override
    public String getURI() {
        return contextURI;
    }

    
    private final HttpHandler hadler = new HttpHandler() {
        @Override
        public void handle(HttpExchange t) throws IOException {
            Map<String,String> query = NetworkUtils.parseURIQuery(t.getRequestURI().getQuery());
            OutputStream out = t.getResponseBody();
            try {
                if (query.containsKey("code")) {
                    AuthInfo authInfo = getToken(query.get("code"));
                    User user = getUserData(authInfo);
                    user.save(dbConnectionfactory);
                    t.sendResponseHeaders(200, user.asJSONObject().toJSONString().getBytes().length); 
                    out.write(user.asJSONObject().toJSONString().getBytes());
                }
                else {
                    t.sendResponseHeaders(200, 0);
                }
            } catch (IOException | ParseException e) {
                JSONObject exception = new JSONObject();
                exception.put("exception", "ioexception");
                exception.put("message", e.getMessage());
                String answer = exception.toJSONString();
                t.sendResponseHeaders(500, answer.getBytes().length);
                out.write(answer.getBytes());
            } catch (Exception ex) {
                Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                out.close();
            }
        }
    };
    
    public AuthInfo getToken(String code) throws IOException{
        try{
            String query = baseTokenQueryURI+code;
            URLConnection connection = new URL(query).openConnection();
            InputStream is = connection.getInputStream();
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject)parser.parse(new InputStreamReader(is));
            return new AuthInfo(
                    (long) json.get("user_id"),
                    (String) json.get("access_token")                    
            );  
        } catch (IOException | ParseException e) {
            throw new IOException(e);
        }
    }
    
    protected static class AuthInfo {
        public final long id;
        public final String token;
    
        public AuthInfo(long id, String token) {
            this.id = id;
            this.token = token;
        }
    }
}
