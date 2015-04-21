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
 * changed by oglandx on 04.13.2015, 21.04.2015
 */
public class FriendDAO {
    
  public static List<Friend> getSingleFriends(final Long idVk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (List<Friend>) session.createCriteria(Friend.class)
                    .add(Restrictions.gt("singleFriend", 1))
                    .add(Restrictions.eq("idVk", idVk))
                    .list();
        } finally {
            session.close();
        }
    }
         
    public static void addSingleFriend(long idVkUser, long idVkFriend) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Friend friend = (Friend)session
                    .createCriteria(Friend.class)
                    .add(Restrictions.eq("singleFriend", 1))
                    .add(Restrictions.eq("idVk", idVkUser))
                    .add(Restrictions.eq("idVkFriend", idVkFriend))
                    .uniqueResult();
            if(friend != null){
                return; // friend already created
            }
            else{
                friend = new Friend();
                friend.setIdVkUser(idVkUser);
                friend.setSingleFriend(true);
                friend.setIdVkFriend(idVkFriend);
            }
            session.beginTransaction();           
            session.save(friend);         
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
        
            
    public static void deleteSingleFriend(long idVkUser, long idVkFriend){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<Friend> friends = (List<Friend>)session
                    .createCriteria(Friend.class)
                    .add(Restrictions.eq("singleFriend", 1))
                    .add(Restrictions.eq("idVk", idVkUser))
                    .add(Restrictions.eq("idVkFriend", idVkFriend))
                    .list();
            if(friends.isEmpty()){
                return;
            }
            session.beginTransaction();
            for (Friend friend: friends){
                session.delete(friend);
            }
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}