package com.example.izual.studentftk.Users;

import com.example.izual.studentftk.Messages.MessageStruct;

import org.json.simple.JSONObject;

/**
 * Created by oglandx on 27.12.2014.
 */
public class UserStruct {
    final String ID;
    final String InstituteID;
    final String SocialToken;
    final String Name;
    final String About;
    final String SocialType;
    final String Surname;
    final String SocialID;
    final String Group;
    final String Photo;

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
        String stringObjects[] = new String[MessageStruct.OBJECTS_COUNT];
        String keys[] = MessageStruct.KEYS;

        for(int i = 0; i < MessageStruct.OBJECTS_COUNT; i++){
            stringObjects[i] = jsonObject.get(keys[i]).toString();
        }
        return stringObjects;
    }
}
