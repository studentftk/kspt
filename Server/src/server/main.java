/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import org.json.simple.parser.ParseException;
import server.httpApi.*;
import utils.NetworkUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.concurrent.Executors;


public class main {

    private static final int threadSizePool = 4;

    public static void main(String[] args) throws IOException, SQLException, ParseException {
        SSLContext sslContext = NetworkUtils.createSSLContext("keystore.jks", "123456", "123456");
        HttpsServer server = HttpsServer.create(new InetSocketAddress(443), 0);
        server.setHttpsConfigurator(new HttpsConfigurator(sslContext));

        DbConnectionFactory db = new DbConnectionFactory();

        addContext(server, new VKApi(db));
        addContext(server, new SendMessageApi(db));
        addContext(server, new GetMessageApi(db));
        addContext(server, new SendMessageApi(db));
        addContext(server, new GetPlacesApi(db));

        server.setExecutor(Executors.newFixedThreadPool(threadSizePool)); // creates a default executor
        server.start();
    }

    private static void addContext(HttpsServer server, HttpApiMethod api) {
        server.createContext(api.getURI(), api.getHandler());
    }
}
