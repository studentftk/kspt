package com.example.izual.studentftk.Network;

import android.app.Activity;

import com.example.izual.studentftk.Common.Utils;

/**
 * Created by oglandx on 26.05.2015.
 */
public abstract class AbstractAndroidLoader {
    protected final Activity activity;
    protected int connectionTimeout;

    protected boolean isError = false;
    protected String errorReason = "";

    public AbstractAndroidLoader(final Activity activity, int connectionTimeout){
        this.activity = activity;
        this.connectionTimeout = connectionTimeout;
    }

    protected void Error(final String message){
        errorReason = message;
        isError = true;
    }

    protected void ShowError(){
        if(isError){
            final String finalErrorReason = errorReason;
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Utils.ShowError(activity, finalErrorReason, true);
                }
            });
        }
    }
}
