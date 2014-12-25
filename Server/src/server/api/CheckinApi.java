package server.api;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.Checkin;
import server.io.JSONHelper;
import server.logic.CheckinDAO;
import server.logic.UserDAO;


public class CheckinApi implements ApiMethod{

    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {
            long idUser;
            long idPlace;
            try {
                idUser = UserDAO.getByToken(params.get("socialToken")).getId();
                idPlace = Long.parseLong(params.get("idPlace"));
            } catch (Exception e) {
                throw new IllegalArgumentException("wrong parameters");
            }
            Checkin checkin = new Checkin()
                    .setIdPlace(idPlace)
                    .setIdUser(idUser);
            CheckinDAO.checkin(checkin);
            return new ApiAnswer(HttpCode.OK, "");
        } catch (Exception ex) {
            String answer = JSONHelper.toJSON(ex);
            Logger.getLogger(CheckinApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ApiAnswer(HttpCode.ERROR, answer);
        }
    }
    
}
