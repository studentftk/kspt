/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.httpApi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import server.DbConnectionFactory;
import server.JSONException;
import server.logic.Message;
import server.logic.User;
import utils.NetworkUtils;

/**
 *
 * @author llama
 */
public class SendMessageApi implements HttpApiMethod{
    private static final String contextURI = "/messages.send";
    private final DbConnectionFactory dbConnectionFactory;
    
    @Override
    public String getURI() {
        return contextURI;
    }
    
    @Override
    public HttpHandler getHandler() {
        return handler;
    }
    
    public SendMessageApi(DbConnectionFactory dbConnectionFactory) {
        this.dbConnectionFactory = dbConnectionFactory;
    }
    
    private final HttpHandler handler = new HttpHandler(){
        @Override
        public void handle(HttpExchange t) throws IOException {
            Map<String,String> query = NetworkUtils.parseURIQuery(t.getRequestURI().getQuery());
            OutputStream out = t.getResponseBody();
            try {
                long id = User.getIdByToken(dbConnectionFactory, query.get("SocialToken"));
                long destination;
                    try{
                        destination = Long.parseLong(query.get("destination"));
                    } catch (Exception e) {
                        throw new IllegalArgumentException("destination must be long");
                    }
                String message = query.get("message");
                if (message==null) throw new IllegalArgumentException("message must not be empty");
                new Message(id, destination, message).send(dbConnectionFactory);
                t.sendResponseHeaders(200, 0); 
            } catch (Exception ex) {
                String answer = JSONException.toJSON(ex);
                t.sendResponseHeaders(500, answer.getBytes().length); 
                out.write(answer.getBytes());
                Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                out.close();
            }
        }
            
    };
    
    
}
