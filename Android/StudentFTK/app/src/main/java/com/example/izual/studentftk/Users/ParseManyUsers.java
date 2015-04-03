package com.example.izual.studentftk.Users;

import com.example.izual.studentftk.Messages.MessageStruct;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

/**
 * Created by oglandx on 04.04.2015.
 */
public class ParseManyUsers {
    private ParseManyUsers(){}

    public static ArrayList<UserStruct> Parse(final String receivedData)
            throws ParseException {
        ArrayList<UserStruct> users = new ArrayList<UserStruct>();

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray)jsonParser.parse(receivedData);

        for(Object objectOfArray: jsonArray){
            JSONObject jsonObject = (JSONObject)objectOfArray;
            users.add(new UserStruct(jsonObject));
        }

        return users;
    }
}
