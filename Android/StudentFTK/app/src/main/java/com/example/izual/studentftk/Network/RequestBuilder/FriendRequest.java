package com.example.izual.studentftk.Network.RequestBuilder;


import java.net.URI;

public class FriendRequest extends Request {
    public static final class Params extends Request.Params{
        public static final String operation = "op";
        public static final String idFriend = "idVkFriend";
    }

    public static class Operations {
        public static final String add = "add";
        public static final String del = "del";
    }

    public static URI BuildFriendRequest( final String socialToken){
        String[] params = { Params.SocialToken};
        String[] values = { socialToken};
        return BaseRequestBuilder.BuildRequest(NameOfSite,
                      Request.Pages.Friends, Request.Methods.Get, params, values);
    }

    public static URI BuildManipFriendRequest(final String idVkFriend, final String operation,
                                              final String socialToken){
        String[] params = { Params.idFriend, Params.operation, Params.SocialToken};
        String[] values = { idVkFriend, operation, socialToken};
        return BaseRequestBuilder.BuildRequest(NameOfSite,
                Request.Pages.Friends, Methods.None, params, values);
    }
}
