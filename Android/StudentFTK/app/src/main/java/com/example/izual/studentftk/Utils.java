package com.example.izual.studentftk;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by oglandx on 22.12.2014.
 */
public class Utils {
    public static void ShowError(Activity activity, final String message){
        Toast toast = Toast.makeText(activity.getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.show();
    }
}
