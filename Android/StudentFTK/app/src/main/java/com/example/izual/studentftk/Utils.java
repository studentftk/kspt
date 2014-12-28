package com.example.izual.studentftk;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by oglandx on 22.12.2014.
 */
public class Utils {
    public static void ShowError(Activity activity, final String message){
        Toast toast = Toast.makeText(activity.getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * First format: 2014-12-08 22:27:39.0
     * Return format: Thu Dec 25 22:32:39
     * @param date
     * @return
     */
    public static String TranslateDateToSer(final String date){
        return "";
    }

    /* Сокрытие активной клавиатуры */
    public static void HideSoftInput(Activity activity){
        InputMethodManager inputMethodManager = (InputMethodManager)
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
