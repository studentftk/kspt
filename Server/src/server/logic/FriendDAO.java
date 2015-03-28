/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.logic;

import org.hibernate.Session;
import server.HibernateUtil;
import server.entity.User;
import java.util.ArrayList;
import java.util.List;
import server.entity.Friend;

import static server.logic.UserDAO.getById;
/**
 *
 * @author Анастасия Тарасова
 */
public class FriendDAO {
    
    public static List<User> getFriends(long id) { //выводим список друзей
        Session session = HibernateUtil.getSessionFactory().openSession();
        List <User> friends = new <User> ArrayList();
        try {
            session.beginTransaction();
            List friendIds = session.createQuery("select idFriend from friend where idUser = " + id).list();

            for(Object friendId: friendIds){
                Long friendIdLong = (Long)friendId;
                friends.add(getById(friendIdLong));
            }

            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return friends;
    }   
        
    public static void addFriend(long idUser, long idFriend) {
        Friend friend = new Friend();
        friend.setIdUser(idUser);
        friend.setIdFriend(idFriend);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();           
            session.save(friend);         
            session.getTransaction().commit();
        } finally {
            session.close();
        };
    }
        
            
    public static void deleteFriend(long idUser, long idFriend){
        Friend friend = new Friend();
        friend.setIdUser(idUser);
        friend.setIdFriend(idFriend);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();           
            session.delete(friend);         
            session.getTransaction().commit();
        } finally {
            session.close();
        };
    }
}
