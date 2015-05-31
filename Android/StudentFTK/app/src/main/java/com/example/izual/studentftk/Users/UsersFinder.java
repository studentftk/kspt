package com.example.izual.studentftk.Users;

import android.app.Activity;

import com.example.izual.studentftk.Common.Utils;
import com.example.izual.studentftk.Network.AbstractAndroidLoader;
import com.example.izual.studentftk.Network.RequestExecutor;

import java.net.URI;
import java.util.HashSet;

/**
 * Created by oglandx on 30.05.2015.
 */
public class UsersFinder extends AbstractAndroidLoader {
    public UsersFinder(final Activity activity, int connectionTimeout){
        super(activity, connectionTimeout);
    }

    public HashSet<UserStruct> Load(URI uri){
        boolean isError = false;
        String errorReason = "";
        HashSet<UserStruct> found = null;
        for (;;) {
            RequestExecutor executor = new RequestExecutor(activity, uri, connectionTimeout);
            try {
                executor.GetThread().join();
            }
            catch (InterruptedException e) {
                isError = true;
                errorReason = e.toString();
                break;
            }
            if (executor.GetTask().isError()) {
                isError = true;
                errorReason = executor.GetTask().getErrorReason();
                break;
            }
            if (executor.GetTask().isDataReady()) {
                try {
                    found = new HashSet<UserStruct>(
                            ParseManyUsers.Parse(executor.GetTask().getData()));
                } catch (Exception e) {
                    isError = true;
                    errorReason = e.toString();
                }
                break;
            } else {
                isError = true;
                errorReason = "Данные ещё не готовы";
                break;
            }
        }
        if(isError){
            final String finalErrorReason = errorReason;
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Utils.ShowError(activity, finalErrorReason, true);
                }
            });
        }
        return found;
    }
}
