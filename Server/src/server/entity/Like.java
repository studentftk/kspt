/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.entity;

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
 * @author Jean
 */
@Entity
@Table(name="like")
public class Like implements JSONAble{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    
    @Column(name="idUser")
    private Long idUser;
    
    @Column(name="idPlace")
    private Long idPlace;
    
    @Column(name="Time")
    private Timestamp time;

    public Like setIdUser(Long idUser) {
        this.idUser = idUser;
        return this;
    }

    public Like setIdPlace(Long idPlace) {
        this.idPlace = idPlace;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public Long getIdPlace() {
        return idPlace;
    }

    public Timestamp getTime() {
        return time;
    }

    @Override
    public JSONObject asJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("idPlace", idPlace);
        json.put("time", time.toString());
        return json;
    }
    
}
