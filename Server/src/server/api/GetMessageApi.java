package server.api;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.JSONException;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.Message;
import server.entity.User;
import server.io.JSONHelper;
import server.logic.MessageDAO;
import server.logic.UserDAO;

public class GetMessageApi implements ApiMethod{
    
    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {
            User user = UserDAO.getUserByToken(params.get("SocialToken"));
            Timestamp from = params.get("from")==null ? new Timestamp(0) : Timestamp.valueOf(params.get("from"));
            List<Message> messages;
            if ("send".equals(params.get("type"))) {
                messages = MessageDAO.getSendedMessages(user.getId(), from);
            } else if ("receive".equals(params.get("type"))){
                messages = MessageDAO.getReceivedMessages(user.getId(), from);
            } else {
                messages = MessageDAO.getAllMessages(user.getId(), from);
            }
            String answer = JSONHelper.toJSON(messages);
            return new ApiAnswer(HttpCode.OK, answer);
        } catch (Exception ex) {
            String answer = JSONException.toJSON(ex);
            Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ApiAnswer(HttpCode.ERROR, answer);
        }
    }
    
}
