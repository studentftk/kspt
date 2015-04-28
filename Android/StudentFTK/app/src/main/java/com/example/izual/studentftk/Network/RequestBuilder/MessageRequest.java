package com.example.izual.studentftk.Network.RequestBuilder;

import com.example.izual.studentftk.Network.RequestBuilder.Utils.URLEncoderRu;

import java.net.URI;

/**
 * Created by oglandx on 22.12.2014.
 */
public class MessageRequest extends Request{

   public static final class Params extends Request.Params {
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



    public static URI BuildRequestGet(final String socialToken,
                                      final String fromDate, final String type){
        String[] params = {Params.SocialToken, Params.From, Params.Type};
        String[] values = {socialToken, fromDate, type};
        return BaseRequestBuilder.BuildRequest(NameOfSite,
                Pages.Messages, Methods.Get, params, values);
    }

    public static URI BuildRequestSend(final String socialToken,
                                       final String destination, final String message){
        String[] params = {Params.SocialToken, Params.Destination, Params.Message};
        String[] values = {socialToken, destination, URLEncoderRu.Encode(message)};
        return BaseRequestBuilder.BuildRequest(NameOfSite,
                Pages.Messages, Methods.Send, params, values);
    }
}
