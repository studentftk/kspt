/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.httpApi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.DbConnectionFactory;
import server.exceptions.NoElementException;
import server.logic.User;
import utils.NetworkUtils;

public class GetUserApi implements HttpApiMethod {

    private final String contextURI = "/user.get";
    private final DbConnectionFactory dbConnectionFactory;
    
    public GetUserApi(DbConnectionFactory dbConnectionFactory) {
        this.dbConnectionFactory = dbConnectionFactory;
    }
    
    private final HttpHandler handler = new HttpHandler(){
        @Override
        public void handle(HttpExchange t) throws IOException {
            Map<String,String> query = NetworkUtils.parseURIQuery(t.getRequestURI().getQuery());
            OutputStream out = t.getResponseBody();
            try {
                long id = User.getIdByToken(dbConnectionFactory, query.get("SocialToken"));
                String answer = User.getUser(dbConnectionFactory, id).asJSONObject().toJSONString();
                t.sendResponseHeaders(200, answer.getBytes().length); 
                out.write(answer.getBytes());
            } catch (NoElementException ex) {
                String answer = ex.toJSON();
                t.sendResponseHeaders(500, answer.getBytes().length); 
                out.write(answer.getBytes());
            } catch (Exception ex) {
                Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                out.close();
            }
        }
            
    };

    @Override
    public HttpHandler getHandler() {
        return handler;
    }

    @Override
    public String getURI() {
        return contextURI;
    }
    
}
