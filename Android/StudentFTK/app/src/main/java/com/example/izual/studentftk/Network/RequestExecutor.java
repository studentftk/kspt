package com.example.izual.studentftk.Network;


import android.app.Activity;

import java.net.URI;

/**
 * Created by oglandx on 27.12.2014.
 */
public class RequestExecutor {
    private RequestTask requestTask = null;
    private Thread execRequest = null;

    public RequestExecutor(Activity activity, URI uri, int timeout){
        requestTask = new RequestTask(activity, uri, timeout);
        execRequest = new Thread(requestTask);
        execRequest.setPriority(Thread.MAX_PRIORITY);
        execRequest.start();
    }

    public RequestTask GetTask(){
        return requestTask;
    }

    public Thread GetThread(){
        return execRequest;
    }
}
