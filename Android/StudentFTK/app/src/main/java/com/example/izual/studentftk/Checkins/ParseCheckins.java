package com.example.izual.studentftk.Checkins;


import com.example.izual.studentftk.Places.PlacesStruct;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class ParseCheckins {
    private ParseCheckins(){}
    public static ArrayList<CheckinsStruct> Parse(final String receivedData)
            throws ParseException {
        ArrayList<CheckinsStruct> places = new ArrayList<CheckinsStruct>();

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray)jsonParser.parse(receivedData);

        for(Object objectOfArray: jsonArray){
            JSONObject jsonObject = (JSONObject)objectOfArray;
            places.add(new CheckinsStruct(jsonObject));
        }

        return places;
    }
}
