package server.api;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.api.params.ParamsChecker;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.Place;
import server.io.JSONHelper;
import server.logic.PlaceDAO;

public class GetPlaceApi implements ApiMethod{

    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {
            ParamsChecker.CheckSecure(params);
            ApiAnswer answer;
            if (params.get("id")!=null) {
                answer = getById(params.get("id"));
            } else 
                answer = getByType(params.get("type"));
            return answer;
        } catch (Exception ex) {
            String answer = JSONHelper.toJSON(ex);
            Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ApiAnswer(HttpCode.ERROR, answer);
        }
    }
    
        
    private ApiAnswer getById(String id){
        Long idPlace;
        try{
            idPlace = Long.parseLong(id);
        } catch (Exception e){
            throw new IllegalArgumentException("id nust be long");
        }
        return new ApiAnswer(HttpCode.OK, PlaceDAO.getPlaceById(idPlace).asJSON().toJSONString());
    }
    
    private ApiAnswer getByType(String type){
        List<Place> result = PlaceDAO.getPlacesByType(type);
        return new ApiAnswer(HttpCode.OK, JSONHelper.toJSON(result));
    }
}
