package server.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import server.DbConnectionFactory;
import utils.JSONException;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.User;
import server.logic.UserDAO;

/* simple test
https://oauth.vk.com/authorize?client_id=4601196&scope=offline&redirect_uri=https://studentspbstu.tk/vk/oauth&v=5.25&response_type=code
*/

public class VKApi implements ApiMethod {
    public final String baseTokenQueryURI;
    private final DbConnectionFactory dbConnectionfactory;

    public VKApi(DbConnectionFactory factory, String redirectURI) {
        this.dbConnectionfactory = factory;
        baseTokenQueryURI = "https://oauth.vk.com/access_token?client_id=4601196&client_secret=4FfKXAErEZYuC9G55RUK&v=5.25&redirect_uri="+redirectURI+"&code=";
    }

    protected User getUserData(AuthInfo authInfo) throws IOException, ParseException {
        String query = "https://api.vk.com/method/users.get?fields=photo_50,first_name,last_name,universities,about&name_case=Nom&v=5.26&user_id="+authInfo.id+" ";
        URLConnection connection = new URL(query).openConnection();
        InputStream is = connection.getInputStream();
        JSONParser parser = new JSONParser();
        Map response  = (Map)parser.parse(new InputStreamReader(is, "UTF-8"));
        JSONArray responseValue = (JSONArray)response.get("response");
        Map<String,String> json = (Map<String,String>)responseValue.get(0);
        
        User user = new User()
                .setName(json.get("first_name"))
                .setSurname(json.get("last_name"))
                .setPhoto((String) json.get("photo_200"))
                .setSocialId(authInfo.id)
                .setSocialType("vk")
                .setSocialToken(authInfo.token);
        return user;
    }
    
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

    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {
            if (params.containsKey("code")) {
                AuthInfo authInfo = getToken(params.get("code"));
                User user = getUserData(authInfo);
                UserDAO.seve(user);
                String answer = user.asJSON().toJSONString();
                return new ApiAnswer(HttpCode.OK, answer);
            }
            else {
                return new ApiAnswer(HttpCode.OK, "");
            }
        } catch (Exception e) {
            String answer = JSONException.toJSON(e);
            Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, e);
            return new ApiAnswer(HttpCode.ERROR, answer);
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
