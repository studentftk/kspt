/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import server.httpApi.VKApi;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import org.json.simple.parser.ParseException;
import server.httpApi.GetUserApi;
import server.httpApi.HttpApiMethod;
import utils.NetworkUtils;


public class main {
    
    public static void main(String[] args) throws IOException, SQLException, ParseException{
        SSLContext sslContext = NetworkUtils.createSSLContext("keystore.jks", "123456", "123456");
        HttpsServer server = HttpsServer.create(new InetSocketAddress(443), 0);
        server.setHttpsConfigurator(new HttpsConfigurator(sslContext));
        
        DbConnectionFactory db = new DbConnectionFactory();
        HttpApiMethod api = new VKApi(db);
        server.createContext(api.getURI(), api.getHandler());
        api = new GetUserApi(db);
        server.createContext(api.getURI(), api.getHandler());
        server.setExecutor(Executors.newFixedThreadPool(4)); // creates a default executor
        server.start();
    }
    
    
}
