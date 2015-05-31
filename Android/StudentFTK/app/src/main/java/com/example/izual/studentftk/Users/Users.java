package com.example.izual.studentftk.Users;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by oglandx on 27.12.2014.
 */
public class Users {
    private Users(){}

    public static Map<String, UserStruct> List; //id -> UserStruct
    public static Map<String, Bitmap> Photos;   //SocialId -> Bitmap

    public static void Init(){
        if(List == null){
            List = new HashMap<String, UserStruct>();
            Photos = new HashMap<String, Bitmap>();
        }
    }

    public static UserStruct GetBySocialId(final String socialType, final String socialId){
        for (final String key: List.keySet()){
            if(List.get(key).SocialType.equals(socialType) &&
                    List.get(key).SocialID.equals(socialId)){
                return List.get(key);
            }
        }
        return null;
    }
}
