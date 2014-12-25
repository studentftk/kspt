/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import javax.net.ssl.SSLContext;
import server.core.Server;
import utils.NetworkUtils;

public class main {

    private static final int threadSizePool = 4;

    public static void main(String[] args) throws IOException{
        SSLContext sslContext = NetworkUtils.createSSLContext("keystore.jks", "123456", "123456");
        Server s = new Server(sslContext, 4);
        s.start();
    }

}
