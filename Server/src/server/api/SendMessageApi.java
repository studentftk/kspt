package server.api;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.JSONException;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.Message;
import server.entity.User;
import server.logic.MessageDAO;
import server.logic.UserDAO;

public class SendMessageApi implements ApiMethod{
    

    @Override
    public ApiAnswer execute(Map<String, String> params) {
       try {
            User user = UserDAO.getUserByToken(params.get("SocialToken"));
            long destination;
                try{
                    destination = Long.parseLong(params.get("destination"));
                } catch (Exception e) {
                    throw new IllegalArgumentException("destination must be long");
                }
            if (params.get("message")==null) throw new IllegalArgumentException("message must not be empty");
            Message message = new Message()
                    .setDesination(destination)
                    .setSender(user.getId())
                    .setMessage(params.get("message"));
            MessageDAO.sendMessage(message);
            return new ApiAnswer(HttpCode.OK, "");
        } catch (Exception ex) {
            String answer = JSONException.toJSON(ex);
            Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ApiAnswer(HttpCode.ERROR, answer);
        }
    }
}
