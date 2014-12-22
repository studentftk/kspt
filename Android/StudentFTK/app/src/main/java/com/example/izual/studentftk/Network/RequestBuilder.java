package com.example.izual.studentftk.Network;

import java.net.URI;

/**
 * Created by oglandx on 22.12.2014.
 */
public class RequestBuilder {
    public static final URI BuildRequest(String nameOfSite,
                                    String page, String method, String[] params, String[] values){
        if(params.length != values.length){
            return null;
        }
        StringBuilder builtParams = new StringBuilder();
        for(int i = 0; i < params.length; i++){
            builtParams.append(params[i]);
            builtParams.append('=');
            builtParams.append(values[i]);
            if(i != params.length - 1){
                builtParams.append('&');
            }
        }
        return URI.create(nameOfSite + page + "." + method + "?" + builtParams.toString());
    }
}
