package server.api;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.User;
import server.io.JSONHelper;
import server.logic.FriendDAO;
import server.logic.UserDAO;

/**
 *
 * @author Анастасия Тарасова
 * 
 * changed by oglandx on 13.04.2015, 15.04.2015
 */


public class GetFriendsApi implements ApiMethod{
    
     @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {            
            if (params.get("idVk") != null) {
                Long idUser = Long.parseLong(params.get("idVk"));
                List friendIds = FriendDAO.getSingleFriends(idUser);
                return new ApiAnswer(HttpCode.OK, JSONHelper.toJSON(friendIds));
            }
            else if (params.get("id") != null) {
                User user = UserDAO.getById(Long.parseLong(params.get("id")));
                List friendIds = FriendDAO.getSingleFriends(user.getSocialId());
                return new ApiAnswer(HttpCode.OK, JSONHelper.toJSON(friendIds));
            }else {
                throw new NullPointerException(
                        "Parameter idVk not found in the query");
            }
        } catch (Exception ex) {
            String answer = JSONHelper.toJSON(ex);
            Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ApiAnswer(HttpCode.ERROR, answer);
        }    
    }
    
}