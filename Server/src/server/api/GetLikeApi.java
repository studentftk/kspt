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
import server.entity.Like;
import server.io.JSONHelper;
import server.logic.LikeDAO;

/**
 *
 * @author Jean
 */
public class GetLikeApi implements ApiMethod{

    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {
            long id;
            int count;
            ParamsChecker.CheckSecure(params);
            try {
                id = Long.parseLong(params.get("id"));
            } catch (Exception e) {
                throw new IllegalArgumentException("id must not be null");
            }
            try {
                count = Integer.parseInt(params.get("count"));
            } catch (Exception e){
                count=10;
            }
            List<Like> result = LikeDAO.getLastLike(id, count);
            return new ApiAnswer(HttpCode.OK, JSONHelper.toJSON(result));
        }  catch (Exception ex) {
            String answer = JSONHelper.toJSON(ex);
            Logger.getLogger(GetLikeApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ApiAnswer(HttpCode.ERROR, answer);
        }  
    }
}
