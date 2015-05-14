package com.example.izual.studentftk.Friends;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class ParseFriends {
    private ParseFriends(){}
    public static ArrayList<FriendsStruct> Parse(final String receivedData)
            throws ParseException {
        ArrayList<FriendsStruct> friends = new ArrayList<FriendsStruct>();

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray)jsonParser.parse(receivedData);

        for(Object objectOfArray: jsonArray){
            JSONObject jsonObject = (JSONObject)objectOfArray;
            friends.add(new FriendsStruct(jsonObject));
        }
        return friends;
    }


}
