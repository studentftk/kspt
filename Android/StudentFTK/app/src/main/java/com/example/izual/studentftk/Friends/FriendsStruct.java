package com.example.izual.studentftk.Friends;

import org.json.simple.JSONObject;

public class FriendsStruct {
    public final String Id;
    public final String IdVkFriend;
    public final String IdVk;
    public final static String[] KEYS = {"id", "idVk", "idVkFriend"};
    public final static int OBJECTS_COUNT = KEYS.length;

    public FriendsStruct(JSONObject jsonObject){
        final String[] data = ParseObject(jsonObject);

        Id = data[0];
        IdVk = data[1];
        IdVkFriend = data[2];
    }

    private String[] ParseObject(JSONObject jsonObject){
        String stringObjects[] = new String[FriendsStruct.OBJECTS_COUNT];
        String keys[] = FriendsStruct.KEYS;

        for(int i = 0; i < FriendsStruct.OBJECTS_COUNT; i++){
            Object parsed = jsonObject.get(keys[i]);
            if(parsed == null){
                stringObjects[i] = "null";
            }
            else {
                stringObjects[i] = parsed.toString();
            }
        }
        return stringObjects;
    }

}
