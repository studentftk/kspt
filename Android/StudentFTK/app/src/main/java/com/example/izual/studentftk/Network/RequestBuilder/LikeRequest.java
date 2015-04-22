package com.example.izual.studentftk.Network.RequestBuilder;

import com.example.izual.studentftk.Network.Request;

import java.net.URI;

public class LikeRequest extends Request {
    public static final class Params {
        public static final String SocialToken = "SocialToken";
        public static final String idPage = "idPage";
    }
    public static URI BuildRequestGet(final String idPage, final String SocialToken) {
        String[] params = {Params.idPage, Params.SocialToken};
        String[] values = {idPage, SocialToken};
        return RequestBuilder.BuildRequest(NameOfSite,
                Pages.Places, Methods.Like, params, values);
    }
}
