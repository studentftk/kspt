/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.entity;

import java.io.Serializable;
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
 * @author oglandx on 04.13.2015
 */

@Entity
@Table(name="friends")
public class Friend implements Serializable, JSONAble{
    
    public Friend() {}
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name="idVk")
    private Long idVk;
    
    @Column(name="singleFriend")
    private Integer singleFriend;
    
    @Column(name="idVkFriend")
    private Long idVkFriend;
    
    @Override
    public JSONObject asJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("idVk", idVk);
        json.put("singleFriend", singleFriend);
        json.put("idVkFriend", idVkFriend); 
        return json;    
    }
    
    public Long getId(){
        return id;
    }
    
    public Long getIdVk() {
        return idVk;
    }
     
    public Long getIdFriend() {
        return idVkFriend;
    }
    
    public boolean isSingleFriend(){
        return singleFriend != 0;
    }
    
    public Friend setId(Long id){
        this.id = id;
        return this;
    }
      
    public Friend setIdVkUser(Long idVkUser) {
        this.idVk = idVkUser;
        return this;
    }
    
    public Friend setIdVkFriend(Long idVkFriend) {
        this.idVkFriend = idVkFriend;
        return this;
    }
    
    public Friend setSingleFriend(boolean singleFriend){
        this.singleFriend = singleFriend ? 1 : 0;
        return this;
    }
}