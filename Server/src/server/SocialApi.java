package server;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.util.Map;
import org.json.simple.parser.ParseException;

public interface SocialApi extends HttpApiMethod {
    public Map getUserData(String id) throws IOException, ParseException;
}
