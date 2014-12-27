package com.example.izual.studentftk.Users;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Created by oglandx on 27.12.2014.
 */
public class ParseUsers {
    private ParseUsers(){}

    public static UserStruct Parse(final String receivedData)
            throws ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParser.parse(receivedData);

        return new UserStruct(jsonObject);
    }
}
