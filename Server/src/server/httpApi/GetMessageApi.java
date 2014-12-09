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
public class GetMessageApi implements HttpApiMethod{

    private static final String contextURI = "/messages.get";
    private final DbConnectionFactory dbConnectionFactory;
    
    @Override
    public HttpHandler getHandler() {
        return handler;
    }

    @Override
    public String getURI() {
        return contextURI;
    }
    
    public GetMessageApi(DbConnectionFactory dbConnectionFactory){
        this.dbConnectionFactory = dbConnectionFactory;
    }
    
    private final HttpHandler handler = new HttpHandler(){
        @Override
        public void handle(HttpExchange t) throws IOException {
            Map<String,String> query = NetworkUtils.parseURIQuery(t.getRequestURI().getQuery());
            OutputStream out = t.getResponseBody();
            try {
                long id = User.getIdByToken(dbConnectionFactory, query.get("SocialToken"));
                Timestamp from = query.get("from")==null ? new Timestamp(0) : Timestamp.valueOf(query.get("from"));
                JSONArray json;
                if ("send".equals(query.get("type"))) {
                    json = Message.getSourceMessages(dbConnectionFactory, id, from);
                } else if ("receive".equals(query.get("type"))){
                    json = Message.getDestinationMessages(dbConnectionFactory, id, from);
                } else {
                    json = Message.getAllMessages(dbConnectionFactory, id, from);
                }
                String answer = json.toJSONString();
                t.sendResponseHeaders(200, answer.getBytes().length); 
                out.write(answer.getBytes());
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
