package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.NetworkUtils;
import org.json.simple.parser.ParseException;

/* simple test
https://oauth.vk.com/authorize?client_id=4601196&redirect_uri=https://studentspbstu.tk/vk/oauth&v=5.25&response_type=code
*/
public class VKApi implements SocialApi{
    public final String contextURI = "/vk/oauth";
    public final String baseTokenQueryURI = "https://oauth.vk.com/access_token?client_id=4601196&client_secret=4FfKXAErEZYuC9G55RUK&v=5.25&redirect_uri="+NetworkUtils.getServerURL()+contextURI+"&code=";
    private final DBManager dbManager;

    public VKApi(DBManager db) {
        this.dbManager = db;
    }
    
    @Override
    public void assignToHttpServer(HttpServer server) {
        server.createContext(contextURI, new ContextHandler());
    }

    @Override
    public Map getUserData(String id) throws IOException, ParseException {
        String query = "https://api.vk.com/method/users.get?fields=photo_50,first_name,last_name,universities,about&name_case=Nom&v=5.26&user_id="+id;
        HashMap<String,String> result = new HashMap<>();
        URLConnection connection = new URL(query).openConnection();
        InputStream is = connection.getInputStream();
        JSONParser parser = new JSONParser();
        Map response  = (Map)parser.parse(new InputStreamReader(is, "UTF-8"));
        JSONArray responseValue = (JSONArray)response.get("response");
        Map<String,String> json = (Map<String,String>)responseValue.get(0);
        String buf = "";  buf = json.get("first_name");  result.put("Name", buf);
        buf = "";         buf = json.get("last_name");   result.put("Surname", buf);
        buf = "";         buf = json.get("photo_50");    result.put("Photo", buf);
        return result;
    }

    
    class ContextHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            Map<String,String> query = NetworkUtils.parseURIQuery(t.getRequestURI().getQuery());
            try (OutputStream out = t.getResponseBody()) {
                if (query.containsKey("code")) {
                    Token answer = getToken(query.get("code"));
                    Map userData = getUserData(answer.id);
                    userData.put("Social_token", answer.token);
                    userData.put("Social_id", answer.id);
                    userData.put("Social_type", "vk");
                    dbManager.updateOrInsertUser(userData);
                    t.sendResponseHeaders(200, answer.token.length()); 
                    out.write(answer.token.getBytes());
                }
                else {
                    t.sendResponseHeaders(200, 0);
                }
            } catch (Exception ex) {
                Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Token getToken(String code) throws IOException{
        try{
            URLConnection connection = new URL(baseTokenQueryURI+code).openConnection();
            InputStream is = connection.getInputStream();
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject)parser.parse(new InputStreamReader(is));
            return new Token(
                    json.get("user_id").toString(),
                    json.get("access_token").toString()
                );  
        } catch (IOException | ParseException e) {
            throw new IOException(e);
        }
    }
    
    class Token {
        public final String id;
        public final String token;

        public Token(String id,String token) {
            this.id = id;
            this.token = token;
        }
        
    }
}
