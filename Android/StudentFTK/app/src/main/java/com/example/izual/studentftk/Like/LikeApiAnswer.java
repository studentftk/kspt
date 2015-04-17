package com.example.izual.studentftk.Like;

/**
 * Created by на on 17.04.2015.
 */
public class LikeApiAnswer {
    public final int httpCode;
    public final String message;

    LikeApiAnswer(int httpCode, String message) {
        this.httpCode = httpCode;
        this.message = message;
    }
}
