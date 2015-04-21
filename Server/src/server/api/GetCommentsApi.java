/*
 * license
 */
package server.api;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.io.JSONHelper;
import server.logic.CommentDAO;
import server.entity.Comment;

/**
 *
 * @author oglandx
 * created on 22.04.2015 by oglandx
 */
public class GetCommentsApi implements ApiMethod{
    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {            
            if(params.get("id") != null){
                Long idComment = Long.parseLong(params.get("id"));
                Comment comment = CommentDAO.getCommentById(idComment);
                return new ApiAnswer(HttpCode.OK, comment.asJSON().toJSONString());
            }
            else if(params.get("type") != null &&
                    params.get("entityId") != null){
                Long entityId = Long.parseLong(params.get("entityId"));
                List comments = null;
                switch (params.get("type")) {
                    case Comment.EntityTypesStr.Places:
                        comments = CommentDAO.getAllComments(
                                Comment.EntityTypes.Places, entityId);
                        break;
                    case Comment.EntityTypesStr.News:
                        comments = CommentDAO.getAllComments(
                                Comment.EntityTypes.News, entityId);
                        break;
                    default:
                        throw new Exception(
                                "This comment type is not supported");
                }
                String answer = JSONHelper.toJSON(comments);
                return new ApiAnswer(HttpCode.OK, answer);
            }
            else if(params.get("idVkUser") != null){
                Long idVkUser = Long.parseLong(params.get("idVkUser"));
                List comments = CommentDAO.getAllCommentsFromUser(idVkUser);
                String answer = JSONHelper.toJSON(comments);
                return new ApiAnswer(HttpCode.OK, answer);
            }
            else{
                throw new Exception("Wrong query parameters");
            }
        } catch (Exception ex) {
            String answer = JSONHelper.toJSON(ex);
            Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ApiAnswer(HttpCode.ERROR, answer);
        }
        
    }
    
}
