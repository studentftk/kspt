package com.example.izual.studentftk.Friends;

import android.app.Activity;

import com.example.izual.studentftk.Common.ProfileInformation;
import com.example.izual.studentftk.Common.Utils;
import com.example.izual.studentftk.Network.AbstractAndroidLoader;
import com.example.izual.studentftk.Network.RequestBuilder.FriendRequest;
import com.example.izual.studentftk.Network.RequestBuilder.Request;
import com.example.izual.studentftk.Network.RequestExecutor;
import com.example.izual.studentftk.Network.RequestTask;

import java.net.URI;

/**
 * Created by oglandx on 01.06.2015.
 */
public class FriendsManagement extends AbstractAndroidLoader{
    public FriendsManagement(final Activity activity, int connectionTimeout){
        super(activity, connectionTimeout);
    }

    public void ManageFriend(final String idVkFriend, final String operation){
        try{
            URI uri = FriendRequest.BuildManipFriendRequest(idVkFriend,
                    operation, ProfileInformation.socialToken);
            RequestExecutor executor = new RequestExecutor(activity, uri, connectionTimeout);
            executor.GetThread().join();
            isError |= executor.GetTask().isError();
            errorReason += executor.GetTask().getErrorReason();
            if(executor.GetTask().isDataReady()){
                if(operation.equals(FriendRequest.Operations.add)) {
                    Utils.ShowError(activity, "Друг успешно добавлен", false);
                } else if(operation.equals(FriendRequest.Operations.del)){
                    Utils.ShowError(activity, "Друг успешно удалён", false);
                } else{
                    Utils.ShowError(activity,
                            "Вы только что успешно сделали непонятное действие", false);
                }
            } else{
                if(operation.equals(FriendRequest.Operations.add)) {
                    Utils.ShowError(activity, "Не удалось добавить друга", false);
                } else if(operation.equals(FriendRequest.Operations.del)){
                    Utils.ShowError(activity, "Не удалось удалить друга", false);
                } else{
                    Utils.ShowError(activity,
                            "Вы только что безуспешно сделали непонятное действие", false);
                }
            }
        }
        catch(Exception e){
            Error(e.toString());
        }
        finally{
            ShowError();
        }
    }
}
