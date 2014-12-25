package server.logic;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import server.HibernateUtil;
import server.entity.Checkin;

public class CheckinDAO {
    public static List<Checkin> getLastCheckins(long idUser, int count){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (List<Checkin>) session.createCriteria(Checkin.class)
                    .add(Restrictions.eq("idUser", idUser))
                    .addOrder(Order.desc("time"))
                    .setMaxResults(count)
                    .list();
        } finally {
            session.close();
        }
    }
    
    public static void checkin(Checkin checkin){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(checkin);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
