package com.example.izual.studentftk.Users;

import com.example.izual.studentftk.Messages.MessageStruct;

import org.json.simple.JSONObject;

/**
 * Created by oglandx on 27.12.2014.
 */
public class UserStruct {
    public final String ID;
    public final String InstituteID;
    public final String SocialToken;
    public final String Name;
    public final String About;
    public final String SocialType;
    public final String Surname;
    public final String SocialID;
    public final String Group;
    public final String Photo;

    public final static String[] KEYS = {"id", "instituteId", "socialToken",
            "name", "about", "socialType", "surname", "socialId", "group", "photo"};
    public final static int OBJECTS_COUNT = KEYS.length;

    public UserStruct(JSONObject jsonObject){
        final String[] data = ParseObject(jsonObject);

        ID = data[0];
        InstituteID = data[1];
        SocialToken = data[2];
        Name = data[3];
        About = data[4];
        SocialType = data[5];
        Surname = data[6];
        SocialID = data[7];
        Group = data[8];
        Photo = data[9];
    }

    private String[] ParseObject(JSONObject jsonObject){
        String stringObjects[] = new String[UserStruct.OBJECTS_COUNT];
        String keys[] = UserStruct.KEYS;

        for(int i = 0; i < UserStruct.OBJECTS_COUNT; i++){
            Object parsedObject = jsonObject.get(keys[i]);
            if(parsedObject == null){
                stringObjects[i] = "null";
            }
            else {
                stringObjects[i] = parsedObject.toString();
            }
        }
        return stringObjects;
    }
}
