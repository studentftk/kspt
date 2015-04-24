/*
 * license
 */
package server.api.params;

import java.util.Map;
import server.entity.User;
import server.logic.UserDAO;
import utils.exceptions.ParameterNotFoundException;

/**
 *
 * @author oglandx
 * created on 22.04.2015 by oglandx
 * 
 */
public class ParamsChecker {
    
    private ParamsChecker(){}
    
    public static User CheckSecure(Map<String, String> params) 
            throws SecurityException {
        String socialToken = params.get("socialToken");
        if(socialToken == null){
            socialToken = params.get("SocialToken");
        }
        if(socialToken == null){
            throw new SecurityException("Parameter socialToken is absent");
        }
        User user = UserDAO.getByToken(socialToken);
        if(user == null){
            throw new SecurityException("Wrong social token");
        }
        if(!UserDAO.isTokenActual(user.getSocialId(), socialToken)){
            throw new SecurityException("Social token is not actual");
        }
        return user;
    }
    
    public static void CheckParams(Map<String, String> inputParams, 
            String... requiredParams) throws ParameterNotFoundException{
        for(String param: requiredParams){
            if(inputParams.get(param) == null){
                throw new ParameterNotFoundException(param);
            }
        }
    }
}
