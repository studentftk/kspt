package com.example.izual.studentftk.Network.RequestBuilder.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by oglandx on 24.04.2015.
 */
public class URLEncoderRu {

    public static final String SpaceCode = "%20";

    public static String CodeChars (final String string, final String oldVal, final String newVal){
        StringBuilder encoded = new StringBuilder(string);
        int replaceIndex = -1;
        while((replaceIndex = encoded.indexOf(oldVal)) != -1){
            encoded.replace(replaceIndex, replaceIndex + oldVal.length(), newVal);
        }
        return encoded.toString();
    }

    public static String CodeSpaces(final String string, final String codeValue){
        return CodeChars(string, " ", codeValue);
    }

    public static String RestoreSpaces(final String string, final String codeValue){
        return CodeChars(string, codeValue, " ");
    }

    public static String CodeSpacesDef(final String string){
        return CodeSpaces(string, SpaceCode);
    }

    public static String RestoreSpacesDef(final String string){
        return RestoreSpaces(string, SpaceCode);
    }

    public static String Encode(final String string){
        try{
            return URLEncoder.encode(CodeSpacesDef(string), "UTF-8");
        }
        catch(UnsupportedEncodingException e){
            return "";
        }
    }
}
