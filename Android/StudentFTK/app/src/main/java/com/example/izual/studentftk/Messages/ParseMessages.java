package com.example.izual.studentftk.Messages;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

/**
 * Created by oglandx on 25.12.2014.
 */
public class ParseMessages {
    private ParseMessages(){}

    public static ArrayList<MessageStruct> Parse(final String receivedData)
                    throws ParseException{
        ArrayList<MessageStruct> messages = new ArrayList<MessageStruct>();

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray)jsonParser.parse(receivedData);

        for(Object objectOfArray: jsonArray){
            JSONObject jsonObject = (JSONObject)objectOfArray;
            messages.add(new MessageStruct(jsonObject));
        }

        return messages;
    }

}
