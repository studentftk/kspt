package com.example.izual.studentftk.Users;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by oglandx on 27.12.2014.
 */
public class Users {
    private Users(){}

    public static Map<String, UserStruct> List; //id -> UserStruct
    public static ArrayList<UserStruct> SearchCache;
    public static Map<String, Drawable> Photos;   // -> Bitmap

    public static void Init(){
        if(List == null){
            List = new HashMap<String, UserStruct>();
        }
        if(SearchCache == null){
            SearchCache = new ArrayList<UserStruct>();
        }
        if(Photos == null){
            Photos = new HashMap<String, Drawable>();
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
