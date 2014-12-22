package com.example.izual.studentftk.Network;

import java.net.URI;

/**
 * Created by oglandx on 22.12.2014.
 */
public class MessageRequest {
    public static final String NameOfSite = "https://studentspbstu.tk/";

    public static final class Pages{
        public static final String Messages = "messages";
    }

    public static final class Methods{
        public static final String Get = "get";
        public static final String Send = "send";
    }

    public static final class Params{
        public static final String SocialToken = "SocialToken";
        public static final String From = "from";
        public static final String Type = "type";
        public static final String Destination = "destination";
        public static final String Message = "message";
    }

    public static final class Types{
        public static final String All = "all";
        public static final String Send = "send";
        public static final String Receive = "receive";
    }

    public static final URI BuildRequestGet(String socialToken, String fromDate, String type){
        String[] params = {Params.SocialToken, Params.From, Params.Type};
        String[] values = {socialToken, fromDate, type};
        return RequestBuilder.BuildRequest(NameOfSite,
                            Pages.Messages, Methods.Get, params, values);
    }

    public static final URI BuildRequestSend(String socialToken,
                                                        String destination, String message){
        String[] params = {Params.SocialToken, Params.Destination, Params.Message};
        String[] values = {socialToken, destination, message};
        return RequestBuilder.BuildRequest(NameOfSite,
                            Pages.Messages, Methods.Send, params, values);
    }
}
