package server.api;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.api.params.ParamsChecker;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.User;
import server.io.JSONHelper;
import server.logic.FriendDAO;



public class GetFriendsApi implements ApiMethod{
    
    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {  
            User user = ParamsChecker.CheckSecure(params);
            List friendIds = FriendDAO.getSingleFriends(user.getSocialId());
            return new ApiAnswer(HttpCode.OK, JSONHelper.toJSON(friendIds));
        } catch (Exception ex) {
            String answer = JSONHelper.toJSON(ex);
            Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ApiAnswer(HttpCode.ERROR, answer);
        }    
    }
    
}