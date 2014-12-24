package server.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.DbConnectionFactory;
import utils.JSONException;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.logic.User;
import utils.NetworkUtils;

public class GetUserApi implements ApiMethod {
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

    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {
            long id = User.getIdByToken(dbConnectionFactory, params.get("SocialToken"));
            String answer = User.getUser(dbConnectionFactory, id).asJSONObject().toJSONString();
            return new ApiAnswer(HttpCode.OK, answer);
        } catch (Exception ex) {
            String answer = JSONException.toJSON(ex);
            Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ApiAnswer(HttpCode.ERROR, answer);
        }    
    }
    
}
