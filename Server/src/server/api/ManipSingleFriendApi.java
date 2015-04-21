/*
 * license
 */
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
 * @author oglandx
 * created on 20.04.2015 by oglandx
 * 
 */
public class ManipSingleFriendApi implements ApiMethod{
    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {            
            Long idVkUser = 0L, idVkFriend = 0L;
            if (params.get("idVk") != null && params.get("idVkFriend") != null){
                idVkUser = Long.parseLong(params.get("idVk"));
                idVkFriend = Long.parseLong(params.get("idVkFriend"));
                
            }
            else if (params.get("id") != null && params.get("idFriend") != null) {
                User user = UserDAO.getById(Long.parseLong(params.get("id")));
                idVkUser = user.getSocialId();
                User friend = UserDAO.getById(
                        Long.parseLong(params.get("idFriend")));   
            }
            else{
                throw new NullPointerException(
                        "Parameters idVk or id not found in the query");
            }
            if(params.get("op") != null && "del".equals(params.get("op"))){
                FriendDAO.deleteSingleFriend(idVkUser, idVkFriend);
            }
            else{// else add friend
                FriendDAO.addSingleFriend(idVkUser, idVkFriend);
            }
            return new ApiAnswer(HttpCode.OK, "");
            
        } catch (Exception ex) {
            String answer = JSONHelper.toJSON(ex);
            Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ApiAnswer(HttpCode.ERROR, answer);
        }    
    }
}
