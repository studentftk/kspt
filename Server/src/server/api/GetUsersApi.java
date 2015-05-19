/*
 * 
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
import server.logic.UserDAO;

/**
 *
 * @author oglandx
 * 
 * Enables to receive http-requests with many IDs or social tokens,
 * and sends all requested information of users.
 */
public class GetUsersApi implements ApiMethod{

    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try{
            ParamsChecker.CheckSecure(params);
        }
        catch(SecurityException e){
            Logger.getLogger(VKApi.class.getName())
                                .log(Level.SEVERE, null, e);
            String errorAnswer = JSONHelper.toJSON(e);
            return new ApiAnswer(HttpCode.ERROR, errorAnswer);
        }
        boolean isParamIDs = params.get("ids") != null;
        boolean isParamSocialTokens = params.get("SocialTokens") != null;
        final String param = 
                isParamIDs && !isParamSocialTokens ? params.get("ids") 
                : isParamSocialTokens ? params.get("SocialTokens")
                : params.get("SocialIds");
        final String [] splitted = param.split("\\$");
        StringBuilder answer = new StringBuilder("[");
        for (String element: splitted){
            try{
                User user = isParamIDs && !isParamSocialTokens ? 
                        UserDAO.getById(Long.parseLong(element))
                        : isParamSocialTokens ? UserDAO.getByToken(element)
                        : UserDAO.getBySocial("vk", Long.parseLong(element));
                answer.append(user.asJSON().toJSONString());
                answer.append(",");
            } catch(Exception e){
                Logger.getLogger(VKApi.class.getName())
                                .log(Level.SEVERE, null, e);
                String errorAnswer = JSONHelper.toJSON(e);
                return new ApiAnswer(HttpCode.ERROR, errorAnswer);
            }
        }
        if(answer.length() != 1){
            answer.replace(answer.length() - 1, answer.length(), "]");
        }
        else{
            answer.append("]");
        }
        return new ApiAnswer(HttpCode.OK, answer.toString());
    }
}
