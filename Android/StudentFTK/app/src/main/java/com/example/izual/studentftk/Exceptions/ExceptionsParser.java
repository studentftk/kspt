package com.example.izual.studentftk.Exceptions;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by oglandx on 28.04.2015.
 */
public class ExceptionsParser {

    public static final class ExceptionHeaders{
        public static final String Exception = "exception";
        public static final String Message = "message";
    }

    public static JSONObject ParseJSON(final String jsonDescription) {
        JSONParser jsonParser = new JSONParser();
        try {
            return (JSONObject) jsonParser.parse(jsonDescription);
        }
        catch(Exception e){
            // если структура другого вида, значит нет исключений нужного формата
            return null;
        }
    }

    public static JSONObject DetectException(final JSONObject jsonObject){
        if(jsonObject == null){
            return null;
        }
        final Object parsedExceptionName = jsonObject.get(ExceptionHeaders.Exception);
        final Object parsedExceptionMessage = jsonObject.get(ExceptionHeaders.Message);
        if(parsedExceptionName != null && parsedExceptionMessage != null){
            return jsonObject;
        }
        return null;
    }

    public static void RaiseException(final JSONObject jsonObject) throws Exception{
        if(jsonObject == null){
            return;
        }
        final String exceptionName = jsonObject.get(ExceptionHeaders.Exception).toString();
        final String exceptionMessage = jsonObject.get(ExceptionHeaders.Message).toString();
        if(exceptionName.equals("NullPointerException")){
            throw new NullPointerException(exceptionMessage);
        }
        else if(exceptionName.equals("IOException")){
            throw new IOException(exceptionMessage);
        }
        else if(exceptionName.equals("ParameterNotFoundException")){
            throw new IllegalArgumentException(exceptionMessage);
        }
        else if(exceptionName.equals("SecurityException")){
            throw new SecurityException(exceptionMessage);
        }
        else {
            throw new Exception(exceptionName + "\n" + exceptionMessage);
        }
    }

    public static void TryToRaise(final String jsonDescription) throws Exception{
        RaiseException(
                DetectException(
                        ParseJSON(jsonDescription)));
    }
}
