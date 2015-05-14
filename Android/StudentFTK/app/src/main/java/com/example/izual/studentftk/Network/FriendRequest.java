package com.example.izual.studentftk.Network;
import com.example.izual.studentftk.Network.RequestBuilder.RequestBuilder;
import java.net.URI;


import java.net.URI;

public class FriendRequest extends Request {
    public static final class Params{
        public static final String SocialToken = "SocialToken";
        public static final String idUser = "idUser";
        public static final String idFriend = "idFriend";
    }
    public static URI BuildRequestGet(final String socialToken,
                                      final String idUser, final String idFriend){
        String[] params = {Params.SocialToken, Params.idUser, Params.idFriend};
        String[] values = {socialToken, idUser, idFriend};
        return RequestBuilder.BuildRequest(NameOfSite,
                Request.Pages.Friends, Request.Methods.Get, params, values);
    }
    public static URI BuildRequestSend(final String socialToken,
                                       final String idUser, final String idFriend){
        String[] params = {Params.SocialToken, Params.idUser, Params.idFriend};
        String[] values = {socialToken, idUser, idFriend};
        return RequestBuilder.BuildRequest(NameOfSite,
                Request.Pages.Friends, Request.Methods.Send, params, values);
    }

    public static URI BuildRequestSendFriend(final String socialToken,
                                       final String idUser){
        String[] params = {Params.SocialToken, Params.idUser};
        String[] values = {socialToken, idUser};
        return RequestBuilder.BuildRequest(NameOfSite,
                Request.Pages.Friends, Request.Methods.Send, params, values);
    }
}

