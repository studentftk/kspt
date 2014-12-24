/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.cedarsoftware.util.io.JsonWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Session;
import server.entity.Message;
import server.logic.MessageDAO;

public class main {

    private static final int threadSizePool = 4;

    public static void main(String[] args) throws IOException{
//        SSLContext sslContext = NetworkUtils.createSSLContext("keystore.jks", "123456", "123456");
//        Server s = new Server(sslContext, 4);
//        s.start();
        
        Message mes2 = new Message();
        mes2.setMessage("Azazaz!");
        mes2.setSender(45L);
        mes2.setDesination(45L);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(mes2);
        Message mes = (Message) session.load(Message.class, 19l);
        System.out.println(mes.getMessage());
        session.getTransaction().commit();
        session.close();
        
        List<Message> messages = MessageDAO.getMessage(45L, Timestamp.valueOf("2014-01-08 22:27:36"));
        for (Message message : messages) {
            System.out.println(message.getMessage());
        }
        
    }

}
