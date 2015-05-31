package com.example.izual.studentftk.Network.RequestBuilder;

import java.net.URI;

/**
 * Created by oglandx on 30.05.2015.
 */
public class FindUsersRequest extends Request {
    public static final class Params extends Request.Params {
        public static final String name = "name";
        public static final String surname = "surname";
    }

    public static URI BuildRequestGetForName(final String name,
                                      final String SocialToken) {
        String[] params = {Params.name, Params.SocialToken};
        String[] values = {name, SocialToken};
        return BaseRequestBuilder.BuildRequest(NameOfSite,
                Pages.Users, Methods.Find, params, values);
    }

    public static URI BuildRequestGetForSurname(final String surname,
                                      final String SocialToken) {
        String[] params = {Params.surname, Params.SocialToken};
        String[] values = {surname, SocialToken};
        return BaseRequestBuilder.BuildRequest(NameOfSite,
                Pages.Users, Methods.Find, params, values);
    }

    public static URI BuildRequestGetForFullName(final String name,
                           final String surname, final String SocialToken) {
        String[] params = {Params.name, Params.surname, Params.SocialToken};
        String[] values = {name, surname, SocialToken};
        return BaseRequestBuilder.BuildRequest(NameOfSite,
                Pages.Users, Methods.Find, params, values);
    }
}
