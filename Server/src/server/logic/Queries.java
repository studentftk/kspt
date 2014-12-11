package server.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Alexander Ulitin on 11.12.2014.
 */
public class Queries {
    public Connection connection;

    public Queries(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement selectPlaces() throws SQLException {
        final String s = "SELECT * FROM `student`.`places`";
        final PreparedStatement statement = connection.prepareStatement(s);
        return statement;
    }
}
