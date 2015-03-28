/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.logic;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.HibernateUtil;
import server.entity.User;
import java.util.ArrayList;

import static server.logic.UserDAO.getById;
/**
 *
 * @author Анастасия Тарасова
 */
public class FriendDAO {
    
    public static User[] getFriends(long id){ //выводим список друзей
    Session session = HibernateUtil.getSessionFactory().openSession();
    try {
        session.beginTransaction();
        List friendIds = session.createQuery("select idFriend from friend where idUser = " + id).list();
        List <user> friends = new <user> ArrayList();

        for(int friendId: friendIds){
            friends.add(getById(idFriend));
        }

        session.getTransaction().commit();
    } finally {
        session.close();
    }
    return friends;
}
        
        public static void addFriend(long idUser, long idFriend){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();           
            session.save(friend);         
            session.getTransaction().commit();
        } finally {
            session.close();
        };
    }
        
            
    public static User deleteFriend(long idUser, long idFriend){
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
