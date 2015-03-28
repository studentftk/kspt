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
import java.sql.Time;
import java.sql.Timestamp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/*import server.DbConnectionFactory;*/

/**
 *
 * @author llama
 */
public class Message {
    
    private static final String table = "messages";
    
    public Long id;
    public Long source;
    public Long destination;
    public String message;
    /**
     * Ignore while sending
     */
    public Timestamp sendTime;
    
    public JSONObject asJsonObject(){
        JSONObject json = new JSONObject();
        if (id!=null) json.put("id", id);
        if (source!=null) json.put("source", source);
        if (destination!=null) json.put("destination", destination);
        if (message!=null) json.put("message", message);
        if (sendTime!=null) json.put("sendTime", sendTime);
        return json;
    }
    
   /*public void send(DbConnectionFactory connectionFactory) throws SQLException{
       try (Connection connection = connectionFactory.getConnection()) {
           String sql = "INSERT INTO "+table+" ( SenderID, MailerID, Message ) VALUES (?, ?, ?)";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setLong(1, source);
           statement.setLong(2, destination);
           statement.setString(3, message);
           statement.execute();
        }
    }*/
    
    public Message(long source, long destination, String message){
        this.source = source;
        this.destination = destination;
        this.message = message;
    }
    
    /*public static JSONArray getDestinationMessages(DbConnectionFactory connectionFactory, long destination, Timestamp from) throws SQLException{
        try (Connection connection = connectionFactory.getConnection()) {
           String sql = "SELECT * from "+table+" WHERE MailerID=? AND time>?";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setLong(1, destination);
           statement.setTimestamp(2, from);
           ResultSet answer = statement.executeQuery();
           JSONArray json = new JSONArray();
           while (answer.next()) json.add(new Message(answer).asJsonObject());
           return json;
        }
    }*/
    
    /*public static JSONArray getSourceMessages(DbConnectionFactory connectionFactory, long sender, Timestamp from) throws SQLException{
        try (Connection connection = connectionFactory.getConnection()) {
           String sql = "SELECT * from "+table+" WHERE SenderID=? AND time>?";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setLong(1, sender);
           statement.setTimestamp(2, from);
           ResultSet answer = statement.executeQuery();
           JSONArray json = new JSONArray();
           while (answer.next()) json.add(new Message(answer).asJsonObject());
           return json;
        }
    }*/
    
    /*public static JSONArray getAllMessages(DbConnectionFactory connectionFactory, long id, Timestamp from) throws SQLException{
        try (Connection connection = connectionFactory.getConnection()) {
           String sql = "SELECT * from "+table+" WHERE (SenderID=? OR  MailerID=?) AND time>?";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setLong(1, id);
           statement.setLong(2, id);
           statement.setTimestamp(3, from);
           ResultSet answer = statement.executeQuery();
           JSONArray json = new JSONArray();
           while (answer.next()) json.add(new Message(answer).asJsonObject());
           return json;
        }
    }*/
  
    
    private Message(ResultSet answer) throws SQLException{
        this.id = answer.getLong("ID");
        this.source = answer.getLong("SenderID");
        this.destination = answer.getLong("MailerID");
        this.sendTime = answer.getTimestamp("Time");
        this.message = answer.getString("Message"); 
    }
    
    
    
}
