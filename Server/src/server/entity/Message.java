package server.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="messages")
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;
    
    @Column(name="Message")
    private String message;
    
    @Column(name="SenderID")
    private long sender;
    
    @Column(name="MAILERID")
    private long desination;
    
    @Column(name="Time")
    private Timestamp time;

    public Message(){};

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    public void setDesination(long desination) {
        this.desination = desination;
    }


    public long getSender() {
        return sender;
    }

    public Timestamp getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public long getDesination() {
        return desination;
    }
    
    

    
}
