package com.example.izual.studentftk.Users;

import android.app.Activity;

import com.example.izual.studentftk.Common.ProfileInformation;
import com.example.izual.studentftk.Network.RequestBuilder.UserRequest;
import com.example.izual.studentftk.Network.RequestExecutor;
import com.example.izual.studentftk.Common.Utils;

import java.net.URI;

/**
 * Created by oglandx on 19.05.2015.
 */
public class UserInformationLoader {

    private final Activity activity;
    private final int connectionTimeout;

    public UserInformationLoader(final Activity activity, int connectionTimeout){
        this.activity = activity;
        this.connectionTimeout = connectionTimeout;
    }

    public UserStruct GetUserInformation(final String ID){
        if(!Users.List.containsKey(ID)){
            LoadUserInformation(ID);
        }
        return Users.List.get(ID);
    }

    /*Загружает информацию о пользователях*/
    public void LoadUserInformation(final String ID) {
        boolean isError = false;
        String errorReason = "";
        URI uri = UserRequest.BuildUserRequest(ID, ProfileInformation.socialToken);
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
                    UserStruct user = ParseUsers.Parse(executor.GetTask().getData());
                    Users.List.put(ID, user);
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
    }
}
