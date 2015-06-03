package com.example.izual.studentftk.Network.RequestBuilder;

import com.example.izual.studentftk.Common.Settings;

/**
 * Created by oglandx on 27.12.2014.
 */
public class Request {
    public static final String NameOfSite = Settings.FULL_SITE_NAME;

    public static class Params{
        public static final String SocialToken = "socialToken";
    }

    public static final class Pages{
        public static final String Messages = "messages";
        public static final String User = "user";
        public static final String Places = "places";
        public static final String Users = "users";
        public static final String Friends = "friends";
    }

    public static final class Methods{
        public static final String None = "";
        public static final String Get = "get";
        public static final String Send = "send";
        public static final String Find = "find";
    }
}
