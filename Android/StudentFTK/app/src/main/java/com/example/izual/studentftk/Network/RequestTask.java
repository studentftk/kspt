package com.example.izual.studentftk.Network;

import android.app.Activity;

import com.example.izual.studentftk.Exceptions.ExceptionsParser;
import com.example.izual.studentftk.Utils;

import org.json.simple.JSONArray;
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
    private String data;
    private int timeout;
    private Activity activity;

    private boolean isError = false;
    private String errorReason;

    public RequestTask(Activity activity, final URI uri, int timeout){
        this.uri = uri;
        this.timeout = timeout;
        this.activity = activity;
    }

    public boolean isDataReady(){
        return dataReady;
    }

    public final String getData(){
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
            final String request = new String(uri.toString().getBytes(), "utf-8");
            URL url = new URL(request);
            connection = (HttpsURLConnection)url.openConnection();

            NetworkUtils.setAllTrusted(connection);
            connection.setConnectTimeout(timeout);
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            data = bufferedReader.readLine();
            ExceptionsParser.TryToRaise(data);
            dataReady = true;
        }
        catch(Exception e){
            isError = true;
            errorReason = e.toString();
            if(activity != null){
                final String reason = errorReason;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Utils.ShowError(activity, reason);
                    }
                });
            }
        }
        finally{
            if(connection != null) {
                connection.disconnect();
            }
        }
    }
}
