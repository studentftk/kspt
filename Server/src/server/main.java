package server;

import java.io.IOException;
import javax.net.ssl.SSLContext;
import server.core.Server;
import server.logic.UserDAO;
import server.entity.User;

import utils.NetworkUtils;

public class main {

    private static final int threadSizePool = 4;

    public static void main(String[] args) throws IOException{
        SSLContext sslContext = NetworkUtils.createSSLContext("keystore.jks", "123456", "123456");
        Server s = new Server(sslContext, 4);
        s.start();  
        
        User testUser = new  User();
        testUser.setName("Анастасия");
        testUser.setSurname("Тарасова");
        testUser.setPhoto("NULL");
        testUser.setSocialToken("NULL");
        testUser.setSocialType("NULL");
        testUser.setAbout("NULL");
        testUser.setGroup("53501/3");
        testUser.setInstituteId(18123);
        testUser.setSocialId(2473622L);
        
        UserDAO.save(testUser);
        
    }
}
