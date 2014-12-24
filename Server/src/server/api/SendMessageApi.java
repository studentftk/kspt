package server.api;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.DbConnectionFactory;
import utils.JSONException;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.logic.User;

public class SendMessageApi implements ApiMethod{
    private final DbConnectionFactory dbConnectionFactory;
    
    public SendMessageApi(DbConnectionFactory dbConnectionFactory) {
        this.dbConnectionFactory = dbConnectionFactory;
    }

    @Override
    public ApiAnswer execute(Map<String, String> params) {
       try {
            long id = User.getIdByToken(dbConnectionFactory, params.get("SocialToken"));
            long destination;
                try{
                    destination = Long.parseLong(params.get("destination"));
                } catch (Exception e) {
                    throw new IllegalArgumentException("destination must be long");
                }
            String message = params.get("message");
            if (message==null) throw new IllegalArgumentException("message must not be empty");
            return new ApiAnswer(HttpCode.OK, "");
        } catch (Exception ex) {
            String answer = JSONException.toJSON(ex);
            Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ApiAnswer(HttpCode.ERROR, answer);
        }
    }
}
