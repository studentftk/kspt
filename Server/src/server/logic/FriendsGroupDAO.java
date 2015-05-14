/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.logic;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.HibernateUtil;
import server.entity.FriendsGroup;

/**
 *
 * @author oglandx
 */
public class FriendsGroupDAO {
    public static List<FriendsGroup> getFriendsGroupById(final Long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (List<FriendsGroup>) session.createCriteria(FriendsGroup.class)
                    .add(Restrictions.eq("idFriendsGroup", id))
                    .list();
        } finally {
            session.close();
        }
    }
}
