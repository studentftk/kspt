package server.logic;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.HibernateUtil;
import server.entity.User;


public class UserDAO {
    public static User getById(long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user;
        try {
            user = (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } finally {
            session.close();
        }
        return user;
    }
    
    public static User getByToken(String token){
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user;
        try {
            user = (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("socialToken", token))
                    .uniqueResult();
        } finally {
            session.close();
        }
        return user;
    }
    
    public static User getBySocial(String socialType, long socialId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user;
        try {
            user = (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("socialType", socialType))
                    .add(Restrictions.eq("socialId", socialId))
                    .uniqueResult();
        } finally {
            session.close();
        }
        return user;
    }
    
    public static void save(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
    
}
