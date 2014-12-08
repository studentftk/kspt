/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.json.simple.JSONObject;
import server.DbConnectionFactory;
import utils.QueryHelper;
import server.exceptions.NoElementException;


public class User {
    static private final String table="users";
    
    public Integer id = null;
    public String name = null;
    public String surname = null;
    public String photo = null;
    public String socialToken = null;
    public String socialType = null;
    public String about = null;
    public String group = null;
    public Integer instituteId = null;
    public Integer socialId = null;
    
    public User(){
        
    }
    
    private User(ResultSet answer) throws SQLException{
        if (!answer.next()) throw new NoElementException("SQL: No user found!");
        id = answer.getInt("ID");
        name = answer.getString("name");
        surname = answer.getString("surname");
        photo = answer.getString("photo");
        socialToken = answer.getString("socialToken");
        socialType = answer.getString("socialType");
        about = answer.getString("about");
        group = answer.getString("group");
        instituteId = answer.getInt("instituteId");
        socialId = answer.getInt("socialId"); 
    }
    
    public JSONObject asJSONObject(){
        JSONObject json = new JSONObject();
        if (id!=null) json.put("id", id);
        if (name!=null) json.put("name", name);
        if (surname!=null) json.put("surname", surname);
        if (photo!=null) json.put("photo", photo);
        if (socialToken!=null) json.put("socialToken", socialToken);
        if (socialType!=null) json.put("socialType", socialType);
        if (about!=null) json.put("about", about);
        if (group!=null) json.put("group", group);
        if (instituteId!=null) json.put("instituteId", instituteId);
        if (socialId!=null) json.put("socialId", socialId);
        return json;
    }
    
    public static User getUser(DbConnectionFactory connectionFactory, int id) throws SQLException {
       try (Connection connection = connectionFactory.getConnection()) {
           String sql = "SELECT * from "+table+" WHERE ID=?";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setInt(1, id);
           ResultSet answer = statement.executeQuery();
           return new User(answer);
        }
    }
    
    public static int getIdByToken(DbConnectionFactory connectionFactory, String token) throws SQLException{
       try (Connection connection = connectionFactory.getConnection()) {
           String sql = "SELECT ID from "+table+" WHERE socialToken=?";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setString(1, token);
           ResultSet answer = statement.executeQuery();
           if (!answer.next()) throw new NoElementException("SQL: There are no user with token: "+token);
           return answer.getInt("ID");
        }
    }
    
    public static int getIdBySocial(DbConnectionFactory connectionFactory, int socialId, String socialType) throws SQLException{
       try (Connection connection = connectionFactory.getConnection()) {
           String sql = "SELECT ID from "+table+" WHERE socialId=? AND socialType=?";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setInt(1, socialId);
           statement.setString(2, socialType);
           ResultSet answer = statement.executeQuery();
           answer.next();
           return answer.getInt("ID");
        }
    }
    
    public void save(DbConnectionFactory connectionFactory) throws SQLException{
        Connection connection = connectionFactory.getConnection();
        try{
            id = User.getUser(connectionFactory, id).id;
            Statement statement = connection.createStatement();
            statement.execute(QueryHelper.mapToSQLUpdate(asJSONObject(), table));
        } catch (NoElementException e){
            Statement statement = connection.createStatement();
            statement.execute(QueryHelper.mapToSQLInsert(asJSONObject(), table));
        } finally {
            connection.close();
        }
    }
    
}
