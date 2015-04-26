/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.logic;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import server.HibernateUtil;
import server.entity.Like;


/**
 *
 * @author Jean
 */
public class LikeDAO {
    public static List<Like> getLastLike(long idUser, int count){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (List<Like>) session.createCriteria(Like.class)
                    .add(Restrictions.eq("idUser", idUser))
                    .addOrder(Order.desc("time"))
                    .setMaxResults(count)
                    .list();
        } finally {
            session.close();
        }
    }
    
    public static void like(Like like){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(like);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}