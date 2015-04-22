package com.example.izual.studentftk.Network;

/**
 * Created by oglandx on 27.12.2014.
 */
public class Request {
    public static final String NameOfSite = "https://studentftk.tk/";

    public static final class Pages{
        public static final String Messages = "messages";
        public static final String User = "user";
        public static final String Places = "places";
		public static final String Friends = "friends";
        public static final String SingleFriend = "SingleFriend";

    }

    public static final class Methods{
        public static final String Get = "get";
        public static final String Send = "send";
        public static final String Manip = "manip";
        public static final String Delete = "del";
        public static final String Like = "like";

    }
}
