package com.example.izual.studentftk.Network;

import com.example.izual.studentftk.Network.Request;
import com.example.izual.studentftk.Network.RequestBuilder;

import java.net.URI;

/**
 * Created by oglandx on 29.12.2014.
 */
public class PlacesRequest extends Request {
    public static final class Params {
        public static final String Type = "type";
    }

    public static URI BuildPlacesRequest(final String place){
        String [] params = {Params.Type};
        String [] values = {place};
        return RequestBuilder.BuildRequest(NameOfSite,
                Request.Pages.Places, Request.Methods.Get, params, values);
    }
}
