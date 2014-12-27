package com.example.izual.studentftk.Users;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by oglandx on 27.12.2014.
 */
public class Users {
    private Users(){}

    public static Map<String, UserStruct> List;

    public static void Init(){
        if(List == null){
            List = new HashMap<String, UserStruct>();
        }
    }
}
