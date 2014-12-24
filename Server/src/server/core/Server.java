package server.core;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import server.DbConnectionFactory;
import server.DbConnectionFactory;
import server.core.HttpApiMehodImpl;
import server.core.HttpApiMethod;
import server.api.GetMessageApi;
import server.api.GetUserApi;
import server.api.SendMessageApi;
import server.api.VKApi;
import utils.NetworkUtils;

public class Server {
    
    private final HttpsServer server;
    private final DbConnectionFactory dbConnctionFactory;

    public Server(SSLContext sslContext, int threads) throws IOException {
        server = HttpsServer.create(new InetSocketAddress(443), 0);
        server.setHttpsConfigurator(new HttpsConfigurator(sslContext));
        server.setExecutor(Executors.newFixedThreadPool(threads)); // creates a default executor
        dbConnctionFactory = new DbConnectionFactory();
        initialize();      
    }
    
    private void initialize(){
        HttpApiMehodImpl getMessages = new HttpApiMehodImpl(
                new GetMessageApi(dbConnctionFactory),
                "/messages.get");
        
        HttpApiMehodImpl getUser = new HttpApiMehodImpl(
                new GetUserApi(dbConnctionFactory),
                "/user.get");
        
        HttpApiMehodImpl sendMessage = new HttpApiMehodImpl(
                new SendMessageApi(dbConnctionFactory),
                "/messages.send");
        
        HttpApiMehodImpl vkApi = new HttpApiMehodImpl(
                new VKApi(dbConnctionFactory, NetworkUtils.getServerURL()+"/vk/oauth"),
                "/vk/oauth");
        
        addMethod(getMessages);
        addMethod(getUser);
        addMethod(sendMessage);
        addMethod(vkApi);
    }
    
    private void addMethod(HttpApiMethod method){
        server.createContext(method.getURI(), method.getHandler());
    }
    
    public void start(){
        server.start();
    }
    
    public void stop(int i){
        server.stop(i);
    }
}
