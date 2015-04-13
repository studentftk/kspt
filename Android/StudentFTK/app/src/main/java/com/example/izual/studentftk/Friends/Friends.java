package com.example.izual.studentftk.Friends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Friends {
    private Friends() {
    }

    public static Map<String, ArrayList<FriendsStruct>> List;

    public static void Init() {
        if (List == null) {
            List = new HashMap<String, ArrayList<FriendsStruct>>();
        }
    }
}
