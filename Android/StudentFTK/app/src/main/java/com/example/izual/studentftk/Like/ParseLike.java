package com.example.izual.studentftk.Like;

import com.example.izual.studentftk.Places.PlacesStruct;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class ParseLike {
    private ParseLike(){}

    public static LikeApiAnswer ParseAnswerFromLikeApi(final String receivedData)
            throws ParseException {
        ArrayList<PlacesStruct> places = new ArrayList<PlacesStruct>();

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray)jsonParser.parse(receivedData);

        // TODO: parse jsonArray

        return new LikeApiAnswer(200, "");
    }
}
