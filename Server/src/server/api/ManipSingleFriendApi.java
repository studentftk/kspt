/*
 * license
 */
package server.api;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.api.params.ParamsChecker;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.User;
import server.io.JSONHelper;
import server.logic.FriendDAO;
import utils.exceptions.ParameterNotFoundException;

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
            User user = ParamsChecker.CheckSecure(params);
            Long idVkUser = user.getSocialId();
            Long idVkFriend = 0L;
            if (params.get("idVkFriend") != null){
                idVkFriend = Long.parseLong(params.get("idVkFriend"));
            }
            else{
                throw new ParameterNotFoundException("idVkFriend");
            }
            if(params.get("op") != null && "del".equals(params.get("op"))){
                FriendDAO.deleteSingleFriend(idVkUser, idVkFriend);
            }
            else{// else add friend
                FriendDAO.addSingleFriend(idVkUser, idVkFriend);
            }
            return new ApiAnswer(HttpCode.OK, "");
            
        } catch (SecurityException | NumberFormatException | 
                ParameterNotFoundException ex) {
            String answer = JSONHelper.toJSON(ex);
            Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ApiAnswer(HttpCode.ERROR, answer);
        }    
    }
}
