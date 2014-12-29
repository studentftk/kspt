package com.example.izual.studentftk.Places;

import org.json.simple.JSONObject;

/**
 * Created by Антон on 29.12.2014.
 */
public class PlacesStruct {

    public final String Id;
    public final String HouseCorp;
    public final String Title;
    public final String Geo;
    public final String Street;
    public final String About;
    public final String HouseNumber;
    public final String Type;
    public final static String[] KEYS = {"id","houseCorp","title","geo","street","about","houseNumber","type"};
    public final static int OBJECTS_COUNT = KEYS.length;

    public PlacesStruct(JSONObject jsonObject) {
        final String[] data = ParseObject(jsonObject);
        Id = data[0];
        HouseCorp = data[1];
        Title = data[2];
        Geo = data[3];
        Street = data[4];
        About = data[5];
        HouseNumber = data[6];
        Type = data[7];
    }

    private String[] ParseObject(JSONObject jsonObject) {
        String stringObjects[] = new String[PlacesStruct.OBJECTS_COUNT];
        String keys[] = PlacesStruct.KEYS;

        for(int i = 0; i < PlacesStruct.OBJECTS_COUNT; i++){
            Object parsedObject = jsonObject.get(keys[i]);
            if(parsedObject == null){
                stringObjects[i] = "null";
            }
            else {
                stringObjects[i] = parsedObject.toString();
            }
        }
        return stringObjects;
    }
}
