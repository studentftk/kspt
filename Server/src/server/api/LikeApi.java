/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.api;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.api.params.ParamsChecker;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.Like;
import server.io.JSONHelper;
import server.logic.LikeDAO;

/**
 *
 * @author Jean
 */
public class LikeApi implements ApiMethod{

    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {
            long idUser;
            long idPlace;
            try {
                idUser = ParamsChecker.CheckSecure(params).getId();
                idPlace = Long.parseLong(params.get("idPlace"));
            } catch (Exception e) {
                throw new IllegalArgumentException("wrong parameters");
            }
            Like like = new Like()
                    .setIdPlace(idPlace)
                    .setIdUser(idUser);
            LikeDAO.like(like);
            return new ApiAnswer(HttpCode.OK, "");
        } catch (Exception ex) {
            String answer = JSONHelper.toJSON(ex);
            Logger.getLogger(LikeApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ApiAnswer(HttpCode.ERROR, answer);
        }
    }
    
}
