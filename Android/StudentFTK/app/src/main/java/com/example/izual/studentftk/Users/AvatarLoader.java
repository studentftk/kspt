package com.example.izual.studentftk.Users;

import android.app.Activity;
import android.graphics.Bitmap;

import com.example.izual.studentftk.Common.Utils;
import com.example.izual.studentftk.Network.AbstractAndroidLoader;
import com.example.izual.studentftk.Network.BitmapLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by oglandx on 26.05.2015.
 */
public class AvatarLoader extends BitmapLoader{
    public AvatarLoader(final Activity activity, int connectionTimeout){
        super(activity, connectionTimeout);
    }

    public Map<String, Bitmap> LoadAvatars(final Set<String> vkIds, int width, int height){
        boolean isError = false;
        String errorReason = "";
        HashMap<String, Bitmap> result = new HashMap<String, Bitmap>();
        for (final String idVk: vkIds){
            if (!Users.Photos.containsKey(idVk)){
                final UserStruct user = Users.GetBySocialId("vk", idVk);
                if (user != null && user.Photo != null) {
                        Users.Photos.put(idVk, GetBitmapFromURL(user.Photo, width, height));
                }
                else{
                    isError = true;
                    errorReason = "User with this social ID is not loaded yet";
                    break;
                }
                result.put(idVk, Users.Photos.get(idVk));
            }
        }
        final String finalErrorReason = errorReason;
        if (isError) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Utils.ShowError(activity, finalErrorReason, true);
                }
            });
        }
        return result;
    }
}
