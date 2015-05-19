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

    public static ArrayList<String> getIds(final String socialToken){
        ArrayList<FriendsStruct> friends = List.get(socialToken);
        ArrayList<String> ids = new ArrayList<String>();
        for (FriendsStruct friend: friends){
            ids.add(friend.IdVkFriend);
        }
        return ids;
    }
}
