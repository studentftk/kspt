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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import utils.NetworkUtils;


public class main {

    private static AtomicLong counter;
    
    public static void main(String[] args) throws IOException, SQLException{

//        SSLContext sslContext = NetworkUtils.createSSLContext("keystore.jks", "123456", "123456");
//        HttpsServer server = HttpsServer.create(new InetSocketAddress(8080), 0);
//        server.setHttpsConfigurator(new HttpsConfigurator(sslContext));
//        new VKApi().assignToHttpServer(server);
//
//        server.setExecutor(Executors.newFixedThreadPool(4)); // creates a default executor
//        server.start();
        counter = new AtomicLong(0);
        DBManager db = new DBManager();
        
        
//        new Thread(){
//            @Override
//            public void run() {
//                while (true){
//                    System.out.println(counter.getAndSet(0));
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
//        }.start();
//        while (true)
//        {
//            db.test(100);
//            counter.incrementAndGet();
//        }
//        ResultSet result = db.getUser(1);
//        System.out.println(result.getString("name"));
        Map<String, Object> record = new HashMap<>();
        record.put("id", 2);
        record.put("name", "Alex");
        db.update(record, "users");
//        db.insert(record, "users");
    }
    
    
}
