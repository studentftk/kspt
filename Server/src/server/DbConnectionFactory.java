package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Layer that provides easy access to progam database
 * @author llama
 */
public class DbConnectionFactory {
    
    /* DB info */
    private final String location = "jdbc:mysql://studentspbstu.tk:3306/student?characterEncoding=utf8";
    private final String user = "root";
    private final String password = "mine11235813";
    
    static {
        try { 
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("There are no Database driver!", ex);
        }
    }

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(location,user, password);
    }
    
    
}
