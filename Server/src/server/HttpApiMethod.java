/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.sun.net.httpserver.HttpServer;

/**
 *
 * @author llama
 */
public interface HttpApiMethod {
    public void assignToHttpServer(HttpServer server);
}
