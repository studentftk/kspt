/*
 * license
 */
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

/**
 *
 * @author oglandx
 * created on 22.04.2015 by oglandx
 */
@Entity
@Table(name = "comments")
public class Comment implements JSONAble, Serializable {
    
    public Comment() {}
    
    public static class EntityTypes{
        public static final int News = 0;
        public static final int Places = 1;
    }
    
    public static class EntityTypesStr{
        public static final String Places = "places";
        public static final String News = "news";
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "entityType")
    private int entityType;
    
    @Column(name = "entityId")
    private Long entityId;
    
    @Column(name = "idVkUser")
    private Long idVkUser;
    
    @Column(name = "idPreviousComment")
    private Long idPreviousComment; //for hierarchical comments
    
    @Column(name = "time")
    private Timestamp time;
    
    @Column(name = "head")
    private String head;
    
    @Column(name = "text")
    private String text;
    
    @Override
    public JSONObject asJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("entityType", entityType);
        json.put("entityId", entityId);
        json.put("idVkUser", idVkUser);
        json.put("idPreviousComment", idPreviousComment);
        json.put("time", time.toString());
        json.put("head", head);
        json.put("text", text);
        return json;    
    }
    
    public Long getId(){
        return id;
    }
    
    public int getEntityType(){
        return entityType;
    }
    
    public Long getEntityId(){
        return entityId;
    }
    
    public Long getIdVkUser(){
        return idVkUser;
    }
    
    public Long getIdPreviousComment(){
        return idPreviousComment;
    }
    
    public String getTime(){
        return time.toString();
    }
    
    public String getHead(){
        return head;
    }
    
    public String getText(){
        return text;
    }
    
    public Comment setEntityType(int entityType){
        this.entityType = entityType;
        return this;
    }
    
    public Comment setEntityId(Long entityId){
        this.entityId = entityId;
        return this;
    }
    
    public Comment setIdVkUser(Long idVkUser){
        this.idVkUser = idVkUser;
        return this;
    }
    
    public Comment setIdPreviousComment(Long idPreviousComment){
        this.idPreviousComment = idPreviousComment;
        return this;
    }
    
    public Comment setHead(final String head){
        this.head = head;
        return this;
    }
    
    public Comment setText(final String text){
        this.text = text;
        return this;
    }
    
}
