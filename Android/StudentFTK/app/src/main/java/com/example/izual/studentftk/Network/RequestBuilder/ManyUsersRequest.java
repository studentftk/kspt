package com.example.izual.studentftk.Network.RequestBuilder;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by oglandx on 03.04.2015.
 */
public class ManyUsersRequest extends Request{

    public static final class Params extends Request.Params {
        public static final String IDs = "ids";
        public static final String SocialTokens = "SocialTokens";
    }

    public static enum DataType{
        TYPE_ID("ids"),
        TYPE_SOCIAL_ID("SocialIds"),
        TYPE_SOCIAL_TOKEN("SocialTokens");

        private final String type;
        DataType(final String type){
            this.type = type;
        }
        public final String get(){
            return type;
        }
    }

    public static final String MakeValues(final ArrayList<String> IDs, final String splitter){
        StringBuilder result = new StringBuilder();
        for(String id : IDs){
            result.append(id);
            result.append(splitter);
        }
        int endPos = result.length() - splitter.length();
        return endPos > 0 ? result.toString().substring(0, endPos) : splitter;
    }

    public static URI BuildManyUsersRequest(final ArrayList<String> IDs,
                                            final DataType dataType, final String socialToken){
        String [] params = {dataType.get(), Params.SocialToken};
        String [] values = {MakeValues(IDs, "$"), socialToken};
        return BaseRequestBuilder.BuildRequest(NameOfSite, Pages.Users, Methods.Get, params, values);
    }

}
