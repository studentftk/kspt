package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.json.simple.JSONObject;

/**
 * Layer that provides easy access to progam database
 * @author llama
 */
public class DBManager {
    
    Connection connection;

    /* DB info */
    private final String location = "jdbc:mysql://192.168.1.161:3306/student?characterEncoding=utf8";
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
    
    public Map getUser(int id) throws SQLException{
        try (Connection dbConnection = getConnection()) {
            Statement statement = dbConnection.createStatement();
            ResultSet answer = statement.executeQuery("SELECT * from users WHERE id=\""+id+"\"");
            return makeMapUser(answer);
        }
    }
    
    public Map getUser(String token) throws SQLException {
       try (Connection dbConnection = getConnection()) {
            Statement statement = dbConnection.createStatement();
            ResultSet answer = statement.executeQuery("SELECT * from users WHERE Social_token=\""+token+"\"");
            return makeMapUser(answer);
        }
    }
    
    public Map getUser(Integer social_id, String social_type) throws SQLException {
       try (Connection dbConnection = getConnection()) {
            Statement statement = dbConnection.createStatement();
            ResultSet answer = statement.executeQuery("SELECT * from users WHERE Social_id=\""+social_id+"\" AND Social_type=\""+social_type+"\"");
            return makeMapUser(answer);
        }
    }
    
    public Map makeMapUser(ResultSet user) throws SQLException{
        Map result = new HashMap();
            if (user.next()) {
                result.put("ID", user.getString("ID"));
                result.put("Name", user.getString("Name"));
                result.put("Surname", user.getString("Surname"));
                result.put("Photo", user.getString("Photo"));
                result.put("Socail_token", user.getString("Social_token"));
                result.put("Social_type", user.getString("Social_type"));
                result.put("About", user.getString("About"));
                result.put("Group", user.getString("Group"));
                result.put("InstituteID", user.getString("InstituteID"));           
                result.put("Social_id", user.getString("Social_id"));    
            }
            return result;
    }
    
    public void updateOrInsertUser(Map userData) throws SQLException{
        try(Connection dbConnection = getConnection()){
            Statement statement = dbConnection.createStatement();
            Map dbUser = getUser(Integer.parseInt(userData.get("Social_id").toString()),userData.get("Social_type").toString());
            if (dbUser.isEmpty())
                statement.execute(QueryHelper.mapToSQLInsert(userData, "users"));
            else {
                userData.put("ID", dbUser.get("ID"));
                statement.execute(QueryHelper.mapToSQLUpdate(userData, "users"));
            }
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
    
    

    
}
