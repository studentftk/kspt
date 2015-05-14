package server.api;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.User;
import server.io.JSONHelper;
import server.logic.UserDAO;

public class GetUserApi implements ApiMethod {
   
    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {
            if (params.get("id")!=null) {
                User user = UserDAO.getById(Long.parseLong(params.get("id")));
                String answer = user.asJSON().toJSONString();
                return new ApiAnswer(HttpCode.OK, answer);
            } else {
                User user = UserDAO.getByToken(params.get("SocialToken"));
                String answer = user.asJSON().toJSONString();
                return new ApiAnswer(HttpCode.OK, answer);
            }
        } catch (Exception ex) {
            String answer = JSONHelper.toJSON(ex);
            Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ApiAnswer(HttpCode.ERROR, answer);
        }    
    }
    
}
