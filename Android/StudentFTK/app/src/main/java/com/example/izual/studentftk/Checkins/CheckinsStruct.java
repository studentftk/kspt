package com.example.izual.studentftk.Checkins;

import org.json.simple.JSONObject;

public class CheckinsStruct {
    public final String Id;
    public final String HouseCorp;
    public final String Title;
    public final static String[] KEYS = {"id","houseCorp","title"};
    public final static int OBJECTS_COUNT = KEYS.length;

    public CheckinsStruct(JSONObject jsonObject) {
        final String[] data = ParseObject(jsonObject);
        Id = data[0];
        HouseCorp = data[1];
        Title = data[2];
    }

    private String[] ParseObject(JSONObject jsonObject) {
        String stringObjects[] = new String[CheckinsStruct.OBJECTS_COUNT];
        String keys[] = CheckinsStruct.KEYS;

        for(int i = 0; i < CheckinsStruct.OBJECTS_COUNT; i++){
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
