package com.example.izual.studentftk.Places;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Антон on 29.12.2014.
 */
public class Places {
    private Places(){}

    public static Map<String, ArrayList<PlacesStruct>> List;

    public static void Init(){
        if(List == null){
            List = new HashMap<String, ArrayList<PlacesStruct>>();
        }
    }
}
