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
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import javax.net.ssl.SSLContext;
import utils.NetworkUtils;


public class main {

    private static AtomicLong counter;
    
    public static void main(String[] args) throws IOException, SQLException{
        SSLContext sslContext = NetworkUtils.createSSLContext("keystore.jks", "123456", "123456");
        HttpsServer server = HttpsServer.create(new InetSocketAddress(443), 0);
        server.setHttpsConfigurator(new HttpsConfigurator(sslContext));
        
        DBManager db = new DBManager();
        new VKApi(db).assignToHttpServer(server);
        new HttpApi(db).assignToHttpServer(server);
        server.setExecutor(Executors.newFixedThreadPool(4)); // creates a default executor
        server.start();

    }
    
    
}
