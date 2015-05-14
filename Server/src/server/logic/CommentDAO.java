/*
 * license
 */
package server.logic;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import server.HibernateUtil;
import server.entity.Comment;

/**
 *
 * @author oglandx
 * created on 22.04.2015 by oglandx
 */
public class CommentDAO {
    public static List<Comment> getAllComments(int entityType, Long entityId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (List<Comment>) session.createCriteria(Comment.class)
                    .add(Restrictions.eq("entityType", entityType))
                    .add(Restrictions.eq("entityId", entityId))
                    .list();
        } finally {
            session.close();
        }
    }
    
    public static List<Comment> getAllCommentsFromUser(Long idVkUser){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (List<Comment>) session.createCriteria(Comment.class)
                    .add(Restrictions.eq("idVkUser", idVkUser))
                    .list();
        } finally {
            session.close();
        }
    }
    
    public static Comment getCommentById(Long commentId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (Comment) session.createCriteria(Comment.class)
                    .add(Restrictions.eq("id", commentId))
                    .uniqueResult();
        } finally {
            session.close();
        }
    }
    
    public static void AddComment(int entityType, Long entityId, Long idVkUser,
            Long idPreviousComment, final String head, final String text){
        Comment comment = new Comment();
        comment.setEntityType(entityType);
        comment.setEntityId(entityId);
        comment.setIdVkUser(idVkUser);
        comment.setIdPreviousComment(idPreviousComment);
        comment.setHead(head);
        comment.setText(text);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();       
            session.save(comment);     
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
    
    public static void DeleteComment(final Long commentId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Comment comment = (Comment) session.createCriteria(Comment.class)
                    .add(Restrictions.eq("id", commentId))
                    .uniqueResult();
            session.beginTransaction();
            session.delete(comment);     
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
