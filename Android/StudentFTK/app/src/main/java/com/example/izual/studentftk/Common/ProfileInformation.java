package com.example.izual.studentftk.Common;

import android.graphics.Bitmap;

/**
 * Created by Izual on 22.12.2014.
 * changed by oglandx on 19.05.2015
 */
public class ProfileInformation {
    public static boolean valid = false;
    public static String socialToken =null ;
    public static String Name = null;
    public static String Surname = null;
    public static Bitmap Photo = null;
    public static String Photo_URL = null;

    public static boolean hasNull(){
        return  socialToken == null ||
                Name == null ||
                Surname == null ||
                Photo == null ||
                Photo_URL == null;
    }
    //public static String person_id = null;
    //public static String socialId=null;
}
