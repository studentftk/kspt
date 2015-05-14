package com.example.izual.studentftk.Network.RequestBuilder;

import java.net.URI;

/**
 * Created by oglandx on 27.12.2014.
 */
public class UserRequest extends Request{

    public static final class Params extends Request.Params {
        public static final String ID = "id";
    }

    public static URI BuildUserRequest(final String ID, final String socialToken){
        String [] params = {Params.ID, Params.SocialToken};
        String [] values = {ID, socialToken};
        return BaseRequestBuilder.BuildRequest(NameOfSite, Pages.User, Methods.Get, params, values);
    }
}
