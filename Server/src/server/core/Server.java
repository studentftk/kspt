package server.core;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import server.api.CheckinApi;
import server.api.GetCheckinsApi;
import server.api.GetMessageApi;
import server.api.GetUserApi;
import server.api.SendMessageApi;
import server.api.VKApi;
import utils.NetworkUtils;

public class Server {
    
    private final HttpsServer server;

    public Server(SSLContext sslContext, int threads) throws IOException {
        server = HttpsServer.create(new InetSocketAddress(443), 0);
        server.setHttpsConfigurator(new HttpsConfigurator(sslContext));
        server.setExecutor(Executors.newFixedThreadPool(threads)); // creates a default executor
        initialize();      
    }
    
    private void initialize(){
        HttpApiMehodImpl getMessages = new HttpApiMehodImpl(
                new GetMessageApi(),
                "/messages.get");
        
        HttpApiMehodImpl getUser = new HttpApiMehodImpl(
                new GetUserApi(),
                "/user.get");
        
        HttpApiMehodImpl sendMessage = new HttpApiMehodImpl(
                new SendMessageApi(),
                "/messages.send");
        
        HttpApiMehodImpl vkApi = new HttpApiMehodImpl(
                new VKApi(NetworkUtils.getServerURL()+"/vk/oauth"),
                "/vk/oauth");
        
        HttpApiMehodImpl getCheckins = new HttpApiMehodImpl(
                new GetCheckinsApi(),
                "/checkins.get");
        
        HttpApiMehodImpl checkinApi = new HttpApiMehodImpl(
                new CheckinApi(),
                "/checkin");
        
        addMethod(getMessages);
        addMethod(getUser);
        addMethod(sendMessage);
        addMethod(vkApi);
        addMethod(getCheckins);
        addMethod(checkinApi);
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
