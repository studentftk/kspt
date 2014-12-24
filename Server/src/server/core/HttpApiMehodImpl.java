/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.core;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import utils.NetworkUtils;

public class HttpApiMehodImpl implements HttpApiMethod {

    final String URI;
    final ApiMethod apiMethod;
    
    public HttpApiMehodImpl(ApiMethod apiMethod, String URI) {
        this.URI = URI;
        this.apiMethod = apiMethod;
    }
    
    @Override
    public HttpHandler getHandler() {
        return handler;
    }

    @Override
    public String getURI() {
        return URI;
    }
    
    private final HttpHandler handler = new HttpHandler() {
        @Override
        public void handle(HttpExchange t) throws IOException {
            Map<String,String> query = NetworkUtils.parseURIQuery(t.getRequestURI().getQuery());
            try (OutputStream out = t.getResponseBody()) {
                ApiMethod.ApiAnswer answer = apiMethod.execute(query);
                t.sendResponseHeaders(answer.httpCode.code, answer.body.getBytes().length);
                out.write(answer.body.getBytes());
            }
        }
    };
    
}
