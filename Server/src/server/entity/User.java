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


@Entity
@Table(name="users")
public class User implements Serializable, JSONAble {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="surname")
    private String surname;
    
    @Column(name="photo")
    private String photo;
    
    @Column(name="socialToken")
    private String socialToken;
    
    @Column(name="socialType")
    private String socialType;
    
    @Column(name="about")
    private String about;
    
    @Column(name="group")
    private String group;
    
    @Column(name="instituteId")
    private Integer instituteId;
    
    @Column(name="socialId")
    private Long socialId;

    @Override
    public JSONObject asJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("name", name);
        json.put("surname", surname);
        json.put("photo", photo);
        json.put("socialToken", socialToken);
        json.put("socialType", socialType);
        json.put("about", about);
        json.put("group", group);
        json.put("instituteId", instituteId);
        json.put("socialId", socialId);
        return json;    
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public User setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public User setSocialToken(String socialToken) {
        this.socialToken = socialToken;
        return this;
    }

    public User setSocialType(String socialType) {
        this.socialType = socialType;
        return this;
    }

    public User setAbout(String about) {
        this.about = about;
        return this;
    }

    public User setGroup(String group) {
        this.group = group;
        return this;
    }

    public User setInstituteId(Integer instituteId) {
        this.instituteId = instituteId;
        return this;
    }

    public User setSocialId(Long socialId) {
        this.socialId = socialId;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoto() {
        return photo;
    }

    public String getSocialToken() {
        return socialToken;
    }

    public String getSocialType() {
        return socialType;
    }

    public String getAbout() {
        return about;
    }

    public String getGroup() {
        return group;
    }

    public Integer getInstituteId() {
        return instituteId;
    }

    public Long getSocialId() {
        return socialId;
    }   
}
