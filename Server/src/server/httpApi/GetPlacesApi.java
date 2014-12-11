package server.httpApi;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import server.DbConnectionFactory;
import server.JsonSerializer;
import server.logic.PlaceReader;
import server.logic.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

/**
 * Created by Alexander Ulitin on 11.12.2014.
 */
public class GetPlacesApi extends JsonMethodHandler {

    private final DbConnectionFactory db;

    public GetPlacesApi(DbConnectionFactory db) {
        this.db = db;
    }

    @Override
    public String getURI() {
        return "/places";
    }

    @Override
    public JSONObject execute(Map<String, String> arguments) throws Exception{
        try (Connection connection = db.getConnection()){
            final Queries queries = new Queries(connection);
            final PreparedStatement statement = queries.selectPlaces();
            final String serialize = JsonSerializer.Serialize(PlaceReader.getPlaces(statement));
            return (JSONObject) new JSONParser().parse(serialize);
        }
    }
}
