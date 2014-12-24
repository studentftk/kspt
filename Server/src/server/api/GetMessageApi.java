package server.api;

import java.sql.Timestamp;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import server.DbConnectionFactory;
import utils.JSONException;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.logic.Message;
import server.logic.User;

public class GetMessageApi implements ApiMethod{

    DbConnectionFactory dbConnectionFactory;
    
    public GetMessageApi(DbConnectionFactory dbConnectionFactory){
        this.dbConnectionFactory = dbConnectionFactory;
    }
    
    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {
            long id = User.getIdByToken(dbConnectionFactory, params.get("SocialToken"));
            Timestamp from = params.get("from")==null ? new Timestamp(0) : Timestamp.valueOf(params.get("from"));
            JSONArray json;
            if ("send".equals(params.get("type"))) {
                json = Message.getSourceMessages(dbConnectionFactory, id, from);
            } else if ("receive".equals(params.get("type"))){
                json = Message.getDestinationMessages(dbConnectionFactory, id, from);
            } else {
                json = Message.getAllMessages(dbConnectionFactory, id, from);
            }
            String answer = json.toJSONString();
            return new ApiAnswer(HttpCode.OK, answer);
        } catch (Exception ex) {
            String answer = JSONException.toJSON(ex);
            Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ApiAnswer(HttpCode.ERROR, answer);
        }
    }
    
}
