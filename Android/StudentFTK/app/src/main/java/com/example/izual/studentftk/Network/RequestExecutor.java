package com.example.izual.studentftk.Network;


import java.net.URI;

/**
 * Created by oglandx on 27.12.2014.
 */
public class RequestExecutor {
    private String data = "";
    private String errorReason = "";

    boolean error = false;
    boolean ready = false;

    public boolean isError(){
        return error;
    }

    public String getErrorReason(){
        return errorReason;
    }

    public boolean isDataReady(){
        return ready;
    }

    public String getData(){
        return data;
    }

    public RequestExecutor(URI uri, int timeout){
        RequestTask requestTask = new RequestTask(uri, timeout);
        final Thread execRequest = new Thread(requestTask);
        execRequest.setPriority(Thread.MAX_PRIORITY);
        execRequest.start();

        try{
            execRequest.join();
        }
        catch(Exception e){
            error = true;
            if(requestTask.isError()) {
                errorReason = e.toString() + ";" + requestTask.getErrorReason();
            }
            else{
                errorReason = e.toString();
            }
            return;
        }
        error = requestTask.isError();
        ready = requestTask.isDataReady();
        data = requestTask.getData();
        errorReason = requestTask.getErrorReason();
    }
}
