package server.logic;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.HibernateUtil;
import server.entity.Message;

public class MessageDAO {
    public static List<Message> getSendedMessages(long id, Timestamp from){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (List<Message>) session.createCriteria(Message.class)
                    .add(Restrictions.eq("source", id))
                    .add(Restrictions.gt("time", from))
                    .list();
        } finally {
            session.close();
        }
    }
    
    public static List<Message> getReceivedMessages(long id, Timestamp from){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (List<Message>) session.createCriteria(Message.class)
                    .add(Restrictions.eq("destination", id))
                    .add(Restrictions.gt("time", from))
                    .list();
        } finally {
            session.close();
        }
    }
    
    public static List<Message> getAllMessages(long id, Timestamp from){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (List<Message>) session.createCriteria(Message.class)
                    .add(
                        Restrictions.or(Restrictions.eq("destination", id), Restrictions.eq("source", id))
                        )
                    .add(Restrictions.gt("time", from))
                    .list();
        } finally {
            session.close();
        }
    }
    
    public static void sendMessage(Message message){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(message);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
