package server.logic;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.HibernateUtil;
import server.entity.Place;

public class PlaceDAO {
    static public List<Place> getPlacesByType(String type){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (List<Place>) session.createCriteria(Place.class)
                    .add(Restrictions.eq("type", type))
                    .list();
        } finally {
            session.close();
        }
    } 
    
    static public Place getPlaceById(Long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (Place) session.createCriteria(Place.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } finally {
            session.close();
        }
    }
}
