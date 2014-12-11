package server.httpApi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.simple.JSONObject;
import server.JSONException;
import server.core.HttpStatus;
import utils.NetworkUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Alexander Ulitin on 10.12.2014.
 */
public abstract class JsonMethodHandler implements HttpHandler, HttpApiMethod {
    @Override
    public HttpHandler getHandler() {
        return this;
    }

    public abstract String getURI();

    @Override
    public final void handle(HttpExchange t) throws IOException {
        OutputStream out = t.getResponseBody();
        try {
            Map<String,String> args = NetworkUtils.parseURIQuery(t.getRequestURI().getQuery());
            String answer = execute(args).toJSONString();
            byte[] bytes = answer.getBytes();
            t.sendResponseHeaders(HttpStatus.OK, bytes.length);
            out.write(bytes);
        } catch (Exception ex) {
            String answer = JSONException.toJSON(ex);
            byte[] answerBytes = answer.getBytes();
            t.sendResponseHeaders(HttpStatus.InternalServerError, answerBytes.length);
            out.write(answerBytes);

            // todo: not sure about how it's work in java
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            out.close();
        }
    }

    public  abstract JSONObject execute(Map<String, String> arguments) throws Exception;
}
