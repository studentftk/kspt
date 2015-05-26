package com.example.izual.studentftk.Users;

import android.app.Activity;

import com.example.izual.studentftk.Common.ProfileInformation;
import com.example.izual.studentftk.Network.RequestBuilder.ManyUsersRequest;
import com.example.izual.studentftk.Network.RequestExecutor;
import com.example.izual.studentftk.Common.Utils;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by oglandx on 19.05.2015.
 */
public class UsersInformationLoader {
    private final Activity activity;
    private int connectionTimeout;

    public UsersInformationLoader(final Activity activity, int connectionTimeout){
        this.activity = activity;
        this.connectionTimeout = connectionTimeout;
    }

    public Collection<UserStruct> GetUsersInformation(final ArrayList<String> IDs,
                                                      final ManyUsersRequest.DataType dataType){
        ArrayList<String> unknownIDs = new ArrayList();
        for (String id : IDs){
            if(!Users.List.containsKey(id)){
                unknownIDs.add(id);
            }
        }
        LoadUsersInformation(unknownIDs, dataType);
        Collection<UserStruct> required = new ArrayList<UserStruct>();
        for (UserStruct user: Users.List.values()){
            if(IDs.contains(user.SocialID)){
                required.add(user);
            }
        }
        return required;
    }

    public void LoadUsersInformation(final ArrayList<String> IDs,
                                     final ManyUsersRequest.DataType dataType){
        boolean isError = false;
        String errorReason = "";
        URI uri = ManyUsersRequest.BuildManyUsersRequest(IDs, dataType, ProfileInformation.socialToken);
        for (;;) {
            RequestExecutor executor = new RequestExecutor(activity,
                    uri, connectionTimeout);
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
                    ArrayList<UserStruct> users =
                            ParseManyUsers.Parse(executor.GetTask().getData());
                    for (UserStruct user: users) {
                        Users.List.put(user.ID, user);
                    }
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
