package com.example.izual.studentftk.Network.RequestBuilder;
import com.example.izual.studentftk.Network.Request;

import java.net.URI;

public class CheckinRequest  extends Request{
    public static final class Params{
        public static final String SocialToken = "SocialToken";
        public static final String idPage = "idPage";
    }
    public static URI BuildCheckinRequest(final String idPage, final String SocialToken){
        String[] params = {Params.idPage, Params.SocialToken};
        String[] values = {idPage, SocialToken};
        return BaseRequestBuilder.BuildRequest(NameOfSite,
                Request.Pages.Checkin, Request.Methods.Send, params, values);
    }
}