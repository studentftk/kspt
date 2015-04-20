/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.logic;

import org.hibernate.Session;
import server.HibernateUtil;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import server.entity.Friend;
/**
 *
 * @author Анастасия Тарасова
 * 
 * changed by oglandx on 04.13.2015
 */
public class FriendDAO {
    
  public static List<Friend> getSingleFriends(final Long idVk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (List<Friend>) session.createCriteria(Friend.class)
                    .add(Restrictions.gt("singleFriend", 0))
                    .add(Restrictions.eq("idVk", idVk))
                    .list();
        } finally {
            session.close();
        }
    }
         
    public static void addSingleFriend(long idUser, long idFriend) {
        Friend friend = new Friend();
        friend.setIdUser(idUser);
        friend.setSingleFriend(true);
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
        
            
    public static void deleteSingleFriend(long idUser, long idFriend){
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