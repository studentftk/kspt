package server.api;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.JSONException;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.User;
import server.logic.UserDAO;

public class GetUserApi implements ApiMethod {
   
    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {
            User user = UserDAO.getUserByToken(params.get("SocialToken"));
            String answer = user.asJSON().toJSONString();
            return new ApiAnswer(HttpCode.OK, answer);
        } catch (Exception ex) {
            String answer = JSONException.toJSON(ex);
            Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ApiAnswer(HttpCode.ERROR, answer);
        }    
    }
    
}
