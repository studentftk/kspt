package server.logic;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.HibernateUtil;
import server.entity.Message;

public class MessageDAO {
    public static List<Message> getMessage(long id, Timestamp from){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (List<Message>) session.createCriteria(Message.class)
                    .add(Restrictions.eq("sender", id))
                    .add(Restrictions.gt("time", from))
                    .list();
        } finally {
            session.close();
        }
    }
}
