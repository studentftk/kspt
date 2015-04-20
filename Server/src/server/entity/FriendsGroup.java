/*
 * @License
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
 * @author oglandx
 */
@Entity
@Table(name = "friendsGroup")
public class FriendsGroup implements Serializable, JSONAble {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "idFriendsGroup")
    private Long idFriendsGroup;
    
    @Column(name = "idVkFriend")
    private Long idVkFriend;
    
    @Column(name = "name")
    private String name;
    
    @Override
    public JSONObject asJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("idFriendGroup", idFriendsGroup);
        json.put("idVkFriend", idVkFriend);
        json.put("name", name);
        return json;    
    }
    
    public FriendsGroup setIdVkFriend(Long id){
        this.idVkFriend = id;
        return this;
    }
    
    public FriendsGroup setName(final String name){
        this.name = name;
        return this;
    }
    
    public Long getId(){
        return id;
    }
    
    public Long getIdFriendsGroup(){
        return idFriendsGroup;
    }
    
    public Long getIdVkFriend(){
        return idVkFriend;
    }
    
    public String getName(){
        return name;
    }
}
