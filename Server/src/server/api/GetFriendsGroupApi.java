/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.api;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.io.JSONHelper;
import server.logic.FriendsGroupDAO;

/**
 *
 * @author oglandx
 */
public class GetFriendsGroupApi implements ApiMethod {

    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try{
            if (params.get("id") != null){
                Long idFriendsGroup = Long.parseLong(params.get("id"));
                List groups = FriendsGroupDAO.getFriendsGroupById(idFriendsGroup);
                return  new ApiAnswer(HttpCode.OK, JSONHelper.toJSON(groups));
            }
            else{
                throw new Exception("Parameter is not \"id\", wrong query");
            }
        }
        catch(Exception ex){
            String answer = JSONHelper.toJSON(ex);
            Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ApiAnswer(HttpCode.ERROR, answer);
        }
    }
    
}
