
package server.api;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.User;
import server.io.JSONHelper;
import server.logic.FriendDAO;
import server.logic.UserDAO;

public class DeleteFriendAPI {
    //@Override
    public ApiMethod.ApiAnswer execute(Map<String, String> params) {
            
        try {           
            
            if (params.get("idUser")!=null) {
                Long idUser = Long.parseLong(params.get("idUser"));            
                Long idFriend = Long.parseLong(params.get("idFriend"));
                FriendDAO.deleteFriend(idUser, idFriend);
                String answer = "Вы удалили пользователя из друзей";
                return new ApiMethod.ApiAnswer(HttpCode.OK, answer);
            } else {
                User user = UserDAO.getByToken(params.get("SocialToken"));
                String answer = user.asJSON().toJSONString();
                return new ApiMethod.ApiAnswer(HttpCode.OK, answer);
            }
          
        } catch (Exception ex) {
            String answer = JSONHelper.toJSON(ex);
            Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ApiMethod.ApiAnswer(HttpCode.ERROR, answer);
        }    
    }
}
