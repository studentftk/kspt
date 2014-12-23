package com.example.izual.studentftk.Network;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by oglandx on 22.12.2014.
 */
public class RequestTask implements Runnable {
    private URI uri;
    private boolean dataReady = false;
    private JSONObject data;

    private boolean isError = false;
    private String errorReason;

    public RequestTask(final URI uri){
        this.uri = uri;
    }

    public boolean isDataReady(){
        return dataReady;
    }

    public final JSONObject getData(){
        return data;
    }

    public boolean isError(){
        return isError;
    }

    public final String getErrorReason(){
        return errorReason;
    }

    @Override
    public void run(){
        HttpsURLConnection connection = null;
        try {
            URL url = new URL(uri.toString());
            connection = (HttpsURLConnection)url.openConnection();

            NetworkUtils.setAllTrusted(connection);
            connection.setConnectTimeout(20);
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            JSONParser parser = new JSONParser();

            data = (JSONObject) parser.parse(inputStreamReader);
            dataReady = true;
        }
        catch(Exception e){
            isError = true;
            errorReason = e.toString();
        }
        finally{
            if(connection != null) {
                connection.disconnect();
            }
        }
    }
}
