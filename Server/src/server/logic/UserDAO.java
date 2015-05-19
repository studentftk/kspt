package server.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
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
            List<User> users = (List<User>) session.createCriteria(User.class)
                    .add(Restrictions.eq("socialType", socialType))
                    .add(Restrictions.eq("socialId", socialId))
                    .list();
            user = users.get(users.size() - 1);
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
    
    public static boolean isTokenActual(Long socialId, final String socialToken){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<User> users = (List<User>) session.createCriteria(User.class)
                    .add(Restrictions.eq("socialId", socialId))
                    .list();
            User last = users.get(users.size() - 1);
            return last.getSocialToken().equals(socialToken);
        } finally {
            session.close();
        }
    }
    
    public static List<User> deleteDuplicates(final List<User> users){
        Map<Long, User> clean_users = new HashMap<>();
        for (User user: users){
            if(!clean_users.containsKey(user.getSocialId())){
                clean_users.put(user.getSocialId(), user);
            }
        }
        return new ArrayList(clean_users.values());
    }
    
    public static List<User> findUsers(final String name, final String surname){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = session.createCriteria(User.class);
            if(name != null){
                criteria.add(Restrictions.ilike("name", name + "%"));
            }
            if(surname != null){
                criteria.add(Restrictions.ilike("surname", surname + "%"));
            }
            return deleteDuplicates((List<User>) criteria.list());
        } finally {
            session.close();
        }
    }
}
