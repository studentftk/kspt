package com.example.izual.studentftk.Messages;

/**
 * Created by oglandx on 25.12.2014.
 */

import org.json.simple.JSONObject;

/**
 * Хранит структуру сообщения в удобном виде
 */
public class MessageStruct {
    public final String Message;
    public final String Source;
    public final String SendTime;
    public final String Destination;

    @Deprecated
    public final String Id = "";


    public final static String[] KEYS = {"message", "source", "sendTime", "destination"};
    public final static int OBJECTS_COUNT = KEYS.length;

    public MessageStruct(JSONObject jsonObject){
        final String[] data = ParseObject(jsonObject);

        Message = data[0];
        Source = data[1];
        SendTime = data[2];
        Destination = data[3];
    }

    /**
     * Функция парсит JSON-объект в массив строк, в соответствии с заданной структурой (ключи)
     * @param jsonObject объект JSON
     * @return массив строк значений
     */
    private String[] ParseObject(JSONObject jsonObject){
        String stringObjects[] = new String[MessageStruct.OBJECTS_COUNT];
        String keys[] = MessageStruct.KEYS;

        for(int i = 0; i < MessageStruct.OBJECTS_COUNT; i++){
            Object parsed = jsonObject.get(keys[i]);
            if(parsed == null){
                stringObjects[i] = "null";
            }
            else {
                stringObjects[i] = parsed.toString();
            }
        }
        return stringObjects;
    }

}
