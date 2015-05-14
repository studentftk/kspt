package com.example.izual.studentftk.Network.RequestBuilder;

import java.net.URI;

/**
 * Created by oglandx on 29.12.2014.
 */
public class PlacesRequest extends Request {
    public static final class Params extends Request.Params {
        public static final String Type = "type";
    }

    public static URI BuildPlacesRequest(final String place, final String socialToken){
        String [] params = {Params.Type, Params.SocialToken};
        String [] values = {place, socialToken};
        return BaseRequestBuilder.BuildRequest(NameOfSite,
                Request.Pages.Places, Request.Methods.Get, params, values);
    }
}
