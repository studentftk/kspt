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
import java.util.Map;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.NetworkUtils;

/* simple test
https://oauth.vk.com/authorize?client_id=4601196&redirect_uri=https://studentspbstu.tk:8080/vk/oauth&v=5.25&response_type=code
*/
public class VKApi implements SocialApi{
    public final String contextURI = "/vk/oauth";
    public final String baseTokenQueryURI = "https://oauth.vk.com/access_token?client_id=4601196&client_secret=4FfKXAErEZYuC9G55RUK&v=5.25&redirect_uri="+NetworkUtils.getServerURL()+contextURI+"&code=";

    @Override
    public void assignToHttpServer(HttpServer server) {
        server.createContext(contextURI, new ContextHandler());
    }

    @Override
    public String getName() {
        return "vkontakte";
    }
    
    class ContextHandler implements HttpHandler {
        public void handle(HttpExchange t) {
            Map<String,String> query = NetworkUtils.parseURIQuery(t.getRequestURI().getQuery());
            try (OutputStream out = t.getResponseBody()) {
                if (query.containsKey("code")) {
                    String token = getToken(query.get("code"));
                    t.sendResponseHeaders(200, token.length());        
                    out.write(token.getBytes());
                }
                else {
                    t.sendResponseHeaders(200, 0);
                    t.getResponseBody().close();
                }
            } catch (IOException e) {
                System.out.println("azaza");
            }
        }
    }
    
    public String getToken(String code){
        String result = "";
        try {
            URLConnection connection = new URL(baseTokenQueryURI+code).openConnection();
            InputStream is = connection.getInputStream();
            JSONParser parser = new JSONParser();
            Map<String,String> json = (Map<String,String>)parser.parse(new InputStreamReader(is));
            result = json.get("access_token");
        } catch (IOException ex) {
            result = "{\"error\":\"ioexception\",\"error_description\":\"Check your URL\"}";
        } catch (ParseException ex) {
            result = "{\"error\":\"parser exception\",\"error_description\":\" at "+ex.getPosition()+"\"}";
        }
        return result;    
    }
}
