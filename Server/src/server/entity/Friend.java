/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.json.simple.JSONObject;
import server.io.JSONAble;
/**
 *
 * @author Анастасия Тарасова
 */

@Entity
@Table(name="friend")
public class Friend implements Serializable, JSONAble{
    
    @Column(name="idUser")
    @Id
    private Long idUser;
    
    @Column(name="idFriend")
    private Long idFriend;
    
    @Override
    public JSONObject asJSON() {
        JSONObject json = new JSONObject();
        json.put("idUser", idUser);
        json.put("idFriend", idFriend);        
        return json;    
    }
    
     public Long getIdUser() {
        return idUser;
    }
     
      public Long getIdFriend() {
        return idFriend;
    }
      
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
      }
    
    public void setIdFriend(Long idFriend) {
        this.idFriend = idFriend;
      }
}
