package utils;

import utils.JSONException;
import com.cedarsoftware.util.io.JsonWriter;

import java.io.IOException;

/**
 * Created by Alexander Ulitin on 11.12.2014.
 */
public class JsonSerializer {
    public static String Serialize(Object obj) {
        try {
            return JsonWriter.objectToJson(obj);
        } catch (IOException e) {
            return JSONException.toJSON(e);
        }
    }
}
