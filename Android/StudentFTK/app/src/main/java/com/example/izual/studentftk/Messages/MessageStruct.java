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
    public final String Id;
    public final String Source;
    public final String SendTime;
    public final String Destination;

    public final static int OBJECTS_COUNT = 5;
    public final static String[] KEYS = {"message", "id", "source", "sendTime", "destination"};

    public MessageStruct(JSONObject jsonObject){
        final String[] data = ParseObject(jsonObject);

        Message = data[0];
        Id = data[1];
        Source = data[2];
        SendTime = data[3];
        Destination = data[4];
    }

    /**
     * Функция парсит JSON-объект в массив строк, в соответствии с заданной структурой (ключи)
     * @param jsonObject объект JSON
     * @return массив строк значений
     */
    private final String[] ParseObject(JSONObject jsonObject){
        String stringObjects[] = new String[MessageStruct.OBJECTS_COUNT];
        String keys[] = MessageStruct.KEYS;

        for(int i = 0; i < MessageStruct.OBJECTS_COUNT; i++){
            stringObjects[i] = (String)jsonObject.get(keys[i]);
        }
        return stringObjects;
    }

}
