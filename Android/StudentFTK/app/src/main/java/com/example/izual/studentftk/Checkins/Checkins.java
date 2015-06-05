package com.example.izual.studentftk.Checkins;

import com.example.izual.studentftk.Places.PlacesStruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Checkins {
    private Checkins(){}

    public static Map<String, ArrayList<CheckinsStruct>> List;

    public static void Init(){
        if(List == null){
            List = new HashMap<String, ArrayList<CheckinsStruct>>();
        }
    }
}
