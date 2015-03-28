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
public class GetFriendApi implements ApiMethod{
    
     @Override
    public ApiAnswer execute(Map<String, String> params) {
        return null;
    }
    
}
