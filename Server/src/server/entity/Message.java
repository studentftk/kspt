package server.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.json.simple.JSONObject;
import server.io.JSONAble;


@Entity
@Table(name="messages")
public class Message implements Serializable, JSONAble {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;
    
    @Column(name="Message")
    private String message;
    
    @Column(name="SenderID")
    private long source;
    
    @Column(name="MAILERID")
    private long destination;
    
    @Column(name="Time")
    private Timestamp time;

    public Message(){};

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public JSONObject asJSON() {
        JSONObject result = new JSONObject();
        result.put("source", source);
        result.put("destination", destination);
        result.put("sendTime", time.toString());
        result.put("message", message);
        return  result;
    }
    
    public Message setMessage(String message) {
        this.message = message;
        return this;
    }

    public Message setSender(long sender) {
        this.source = sender;
        return this;
    }

    public Message setDesination(long desination) {
        this.destination = desination;
        return this;
    }

    public long getSender() {
        return source;
    }

    public Timestamp getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public long getDesination() {
        return destination;
    }
    
}
