package com.example.izual.studentftk.Common;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
/**
 * Created by oglandx on 22.12.2014.
 */
public class Utils {
    public static void ShowError(Activity activity, final String message, boolean debug){
        if(Settings.DEBUG && debug || !debug) {
            Toast toast = Toast.makeText(activity.getApplicationContext(), message,
                    Toast.LENGTH_LONG);
            toast.show();
        }
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

    /**
     * Адаптация существительного к числительному.
     * @param number числительное
     * @param nominative_singular существительное в именительном падеже ед.ч.
     * @param genitive_singular существительное в родительном падеже ед.ч.
     * @param genitive_plural существительное в родительном падеже мн.ч.
     * @return форма существительного, соответствующая числительному
     */
    public static String AdaptToNumeric( final int number,
                                         final String nominative_singular,
                                         final String genitive_singular,
                                         final String genitive_plural){
        if(number < 0){
            return "";
        }
        final String num = Integer.toString(number);
        if(num.length() == 1 || num.charAt(num.length() - 2) != '1') {
            if (num.charAt(num.length() - 1) == '1') {
                return nominative_singular;
            } else if ( num.charAt(num.length() - 1) == '2' ||
                        num.charAt(num.length() - 1) == '3' ||
                        num.charAt(num.length() - 1) == '4'){
                return genitive_singular;
            }
        }
        return genitive_plural;
    }
}
