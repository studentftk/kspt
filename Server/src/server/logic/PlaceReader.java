package server.logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Ulitin on 11.12.2014.
 */
public class PlaceReader {
    public static Iterable<Place> getPlaces(PreparedStatement statement) throws SQLException {
        List<Place> places = new ArrayList<>();

        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                assert places.add(new Place(
                                rs.getInt("ID"),
                                rs.getString("Type"),
                                rs.getString("street"),
                                rs.getInt("HouseNumber"),
                                rs.getInt("HouseCorp"),
                                rs.getString("Geo"),
                                rs.getString("Actual"))
                );
            }
        }
        return places;
    }
}
