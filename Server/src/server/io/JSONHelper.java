
package server.io;

import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONHelper {
    public static String toJSON(List<? extends JSONAble> source){
        JSONArray result = new JSONArray();
        for (JSONAble element : source) {
            result.add(element.asJSON());
        }
        return result.toJSONString();
    }
    
    public static String toJSON(Exception e){
        JSONObject json = new JSONObject();
        json.put("exception", e.getClass().getName());
        json.put("message", e.getMessage());
        return json.toJSONString();
    }
    
}
