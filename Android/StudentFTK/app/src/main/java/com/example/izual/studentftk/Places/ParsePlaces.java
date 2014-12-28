package com.example.izual.studentftk.Places;

import com.example.izual.studentftk.Users.UserStruct;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by Антон on 29.12.2014.
 */
public class ParsePlaces {
    private ParsePlaces(){}

    public static PlacesStruct Parse(final String receivedData)
            throws ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParser.parse(receivedData);

        return new PlacesStruct(jsonObject);
    }
}
