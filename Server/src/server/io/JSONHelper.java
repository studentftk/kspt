
package server.io;

import java.util.List;
import org.json.simple.JSONArray;

public class JSONHelper {
    public static String toJSON(List<? extends JSONAble> source){
        JSONArray result = new JSONArray();
        for (JSONAble element : source) {
            result.add(element.asJSON());
        }
        return result.toJSONString();
    }
}
