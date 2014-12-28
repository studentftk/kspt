package com.example.izual.studentftk.Places;

import org.json.simple.JSONObject;

/**
 * Created by Антон on 29.12.2014.
 */
public class PlacesStruct {

    public final String ID;
    public final static String[] KEYS = {"id","houseCorp","title","geo","street","about","houseNumber","type"};
    public final static int OBJECTS_COUNT = KEYS.length;

    public PlacesStruct(JSONObject jsonObject) {
        final String[] data = ParseObject(jsonObject);
        ID = data[0];
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
