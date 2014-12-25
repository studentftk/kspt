package server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.json.simple.JSONObject;
import server.io.JSONAble;

@Entity
@Table(name="places")
public class Place implements JSONAble{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    
    @Column(name="type")
    private String type;
    
    @Column(name="title")
    private String title;
    
    @Column(name="street")
    private String street;
    
    @Column(name="houseNumber")
    private Long houseNumber;
    
    @Column(name="houseCorp")
    private String houseCorp;
    
    @Column(name="Geo")
    private String geo;
    
    @Column(name="About")
    private String about;

    public Place setType(String type) {
        this.type = type;
        return this;
    }

    public Place setTitle(String title) {
        this.title = title;
        return this;
    }

    public Place setStreet(String street) {
        this.street = street;
        return this;
    }

    public Place setHouseNumber(Long houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    public Place setHouseCorp(String houseCorp) {
        this.houseCorp = houseCorp;
        return this;
    }

    public Place setGeo(String geo) {
        this.geo = geo;
        return this;
    }

    public Place setAbout(String about) {
        this.about = about;
        return this;
    }

    @Override
    public JSONObject asJSON() {
        JSONObject json = new JSONObject();
        json.put("geo", geo);
        json.put("type", type);
        json.put("title", title);
        json.put("street", street);
        json.put("houseNumber", houseNumber);
        json.put("houseCorp", houseCorp);
        json.put("about", about);
        json.put("id", id);
        return json;
    }
    
    
    
}
