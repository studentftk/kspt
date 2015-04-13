package com.example.izual.studentftk.Network.RequestBuilder;
import com.example.izual.studentftk.Network.RequestBuilder.Request;
import com.example.izual.studentftk.Network.RequestBuilder.RequestBuilder;

import java.net.URI;

public class FriendRequest extends Request {
public static final class Params{
        public static final String SocialToken = "socialToken";
        public static final String idUser = "idUser";
        public static final String idFriend = "idFriend";
    }
public static URI BuildRequestGet(final String socialToken, final String idUser, final String idFriend){
        String[] params = {Params.SocialToken, Params.idUser, Params.idFriend};
        String[] values = {socialToken, idUser, idFriend};
        return RequestBuilder.BuildRequest(NameOfSite,
                Request.Pages.Friends, Request.Methods.Get, params, values);
    }
public static URI BuildRequestSend(final String socialToken, final String idUser, final String idFriend){
        String[] params = {Params.SocialToken, Params.idUser, Params.idFriend};
        String[] values = {socialToken, idUser, idFriend};
        return RequestBuilder.BuildRequest(NameOfSite,
                       Request.Pages.Friends, Request.Methods.Send, params, values);
    }

       public static URI BuildFriendRequest(final String idUser){
        String[] params = {Params.idUser};
       // String[] params = {Params.SocialToken, Params.idUser};
        String[] values = {idUser};
       // String[] values = {socialToken, idUser};
        return RequestBuilder.BuildRequest(NameOfSite,
                       Request.Pages.Friends, Request.Methods.Send, params, values);
    }
}