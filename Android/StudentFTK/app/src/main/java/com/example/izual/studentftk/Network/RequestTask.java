package com.example.izual.studentftk.Network;

import com.example.izual.studentftk.NetworkUtils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by oglandx on 22.12.2014.
 */
public class RequestTask implements Runnable {
    private URI uri;
    private boolean dataReady = false;
    private String data;

    private boolean isError = false;
    private String errorReason;

    public RequestTask(final URI uri){
        this.uri = uri;
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
        try {
            //SSLSocketFactory socketFactory = new SSLSocketFactory(new TrustStrategy)
            //??? how to do this certificate trusted?
            /*HttpGet httpGet = new HttpGet(uri);
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(httpGet);
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(response.getEntity().getContent()));*/
            data = new GetMethodEx().getInternetData(uri);
            dataReady = true;
        }
        catch(Exception e){
            isError = true;
            errorReason = e.toString();
        }
    }
}
