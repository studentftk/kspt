package server.core;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import server.api.CheckinApi;
import server.api.GetCheckinsApi;
import server.api.GetCommentsApi;
import server.api.GetFriendsApi;
import server.api.GetFriendsGroupApi;
import server.api.GetMessageApi;
import server.api.GetPlaceApi;
import server.api.GetUserApi;
import server.api.GetUsersApi;
import server.api.ManipCommentsApi;
import server.api.ManipSingleFriendApi;
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
        
        HttpApiMehodImpl getCheckinsApi = new HttpApiMehodImpl(
                new GetCheckinsApi(),
                "/checkins.get");
        
        HttpApiMehodImpl checkinApi = new HttpApiMehodImpl(
                new CheckinApi(),
                "/checkin");
        
        HttpApiMehodImpl getPlacesApi = new HttpApiMehodImpl(
                new GetPlaceApi(),
                "/places.get");
        
        HttpApiMehodImpl getUsers = new HttpApiMehodImpl(
                new GetUsersApi(),
                "/users.get");
        
        HttpApiMehodImpl getSingleFriends = new HttpApiMehodImpl(
                new GetFriendsApi(),
                "/friends.get");
        
        HttpApiMehodImpl getFriendsGroup = new HttpApiMehodImpl(
                new GetFriendsGroupApi(),
                "/fg.get");
        
        HttpApiMehodImpl manipSingleFriends = new HttpApiMehodImpl(
                new ManipSingleFriendApi(),
                "/friends.manip");
        
        HttpApiMehodImpl getComments = new HttpApiMehodImpl(
                new GetCommentsApi(),
                "/comments.get");
        
        HttpApiMehodImpl manipComments = new HttpApiMehodImpl(
                new ManipCommentsApi(),
                "/comments.manip");
        
        addMethod(getMessages);
        addMethod(getUser);
        addMethod(sendMessage);
        addMethod(vkApi);
        addMethod(getCheckinsApi);
        addMethod(checkinApi);
        addMethod(getPlacesApi);
        addMethod(getUsers);
        addMethod(getSingleFriends);
        addMethod(getFriendsGroup);
        addMethod(manipSingleFriends);
        addMethod(getComments);
        addMethod(manipComments);
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
