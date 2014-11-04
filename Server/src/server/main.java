/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import utils.NetworkUtils;


public class main {

    public static void main(String[] args) throws IOException{

        SSLContext sslContext = NetworkUtils.createSSLContext("keystore.jks", "123456", "123456");
        HttpsServer server = HttpsServer.create(new InetSocketAddress(8080), 0);
        server.setHttpsConfigurator(new HttpsConfigurator(sslContext));
        new VKApi().assignToHttpServer(server);

        server.setExecutor(Executors.newFixedThreadPool(4)); // creates a default executor
        server.start();
        
    }
    
    
}
