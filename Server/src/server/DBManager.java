package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 * Layer that provides easy access to progam database
 * @author llama
 */
public class DBManager {
    
    Connection connection;

    /* DB info */
    private final String tableUsers = "users";
    private final String location = "jdbc:mysql://192.168.1.162:3306/student";
    private final String user = "root";
    private final String password = "mine11235813";
    
    public DBManager (){
        try { 
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("There are no DB driver!", ex);
        }
    }
    
    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(location,user, password);
    }
    
    public ResultSet getUser(Integer id) throws SQLException{
        try (Connection dbConnection = getConnection()) {
            Statement statement = dbConnection.createStatement();
            return statement.executeQuery("SELECT * from "+tableUsers+" WHERE id="+id);
        }
    }
    
    public void insert(Map<String,Object> record, String tableName) throws SQLException{
        try (Connection dbConnection = getConnection()){
            Statement statement = dbConnection.createStatement();    
            statement.execute(QueryHelper.mapToSQLInsert(record, tableName));
        }
    }
    
    public void update(Map<String,Object> record, String tableName) throws SQLException{
        try (Connection dbConnection = getConnection()){
            Statement statement = dbConnection.createStatement();    
            statement.execute(QueryHelper.mapToSQLInsert(record, tableName));
        }
    }
    
    public void test(int n) throws SQLException{
        try (Connection dbConnection = getConnection()){
            dbConnection.setAutoCommit(false);
            Statement statement = dbConnection.createStatement();    
            String query = "INSERT INTO users (name) VALUES (\"Petya\");";
            for (int i=0; i<n;i++)
                statement.addBatch(query);
            statement.executeBatch();
            dbConnection.commit();
        }
    }
    

    
}
