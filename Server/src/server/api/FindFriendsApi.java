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
import server.api.params.ParamsChecker;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.io.JSONHelper;
import server.logic.UserDAO;
import utils.exceptions.ParameterNotFoundException;

/**
 *
 * @author oglandx
 */
public class FindFriendsApi implements ApiMethod{

    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try{
            ParamsChecker.CheckSecure(params);
            if(params.get("name") == null && params.get("surname") == null){
                throw new IllegalArgumentException(
                        "Not found name and surname params");
            }
            List found = UserDAO
                        .findUsers(params.get("name"), params.get("surname"));
            String answer = JSONHelper.toJSON(found);
            return new ApiAnswer(HttpCode.OK, answer);
        }
        catch(SecurityException | IllegalArgumentException e){
            String answer = JSONHelper.toJSON(e);
            Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, e);
            return new ApiAnswer(HttpCode.ERROR, answer);
        }
    }
    
}
