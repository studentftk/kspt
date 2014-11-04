package server;

import com.sun.net.httpserver.HttpServer;

public interface SocialApi {
    public void assignToHttpServer(HttpServer server);
    public String getName();
}
