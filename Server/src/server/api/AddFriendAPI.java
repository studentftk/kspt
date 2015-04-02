
package server.api;

import static com.mchange.v2.c3p0.impl.C3P0Defaults.user;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.io.JSONHelper;
import server.logic.FriendDAO;
import server.entity.User;
import server.logic.UserDAO;

public class AddFriendApi implements ApiMethod{
    @Override
    public ApiMethod.ApiAnswer execute(Map<String, String> params) {
            
        try {           
            
            if (params.get("idUser")!=null) {
                Long idUser = Long.parseLong(params.get("idUser"));            
                Long idFriend = Long.parseLong(params.get("idFriend"));
                FriendDAO.addFriend(idUser, idFriend);
                String answer = "Вы добавили пользователя в друзья";
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
