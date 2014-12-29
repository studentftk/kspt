package com.example.izual.studentftk.Places;

import com.example.izual.studentftk.Messages.MessageStruct;
import com.example.izual.studentftk.Users.UserStruct;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

/**
 * Created by Антон on 29.12.2014.
 */
public class ParsePlaces {
    private ParsePlaces(){}

    public static ArrayList<PlacesStruct> Parse(final String receivedData)
            throws ParseException{
        ArrayList<PlacesStruct> places = new ArrayList<PlacesStruct>();

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray)jsonParser.parse(receivedData);

        for(Object objectOfArray: jsonArray){
            JSONObject jsonObject = (JSONObject)objectOfArray;
            places.add(new PlacesStruct(jsonObject));
        }

        return places;
    }
}
