/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import utils.NetworkUtils;

/**
 *
 * @author llama
 */
public class HttpApi implements HttpApiMethod{

    private final DBManager db;  
    
    public HttpApi(DBManager db) {
        this.db = db;
        allApi.add(new getUserApi());
    }

    
    private List<HttpApiMethod> allApi = new LinkedList();
    
    @Override
    public void assignToHttpServer(HttpServer server) {
        for (HttpApiMethod elem : allApi) {
             elem.assignToHttpServer(server);
        }
    }
    
class getUserApi implements HttpApiMethod {

    private String contextURI = "/user.get";
    
    private HttpHandler handler = new HttpHandler(){
        @Override
        public void handle(HttpExchange t) throws IOException {
            Map<String,String> query = NetworkUtils.parseURIQuery(t.getRequestURI().getQuery());
            try (OutputStream out = t.getResponseBody()) {
                JSONObject userData = new JSONObject(db.getUser(query.get("Social_token")));
                String answer = userData.toJSONString();
                t.sendResponseHeaders(200, answer.getBytes().length); 
                out.write(answer.getBytes());
            } catch (Exception ex) {
                Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    };
    
    @Override
    public void assignToHttpServer(HttpServer server) {
        server.createContext(contextURI, handler);
    }
    
}
}

