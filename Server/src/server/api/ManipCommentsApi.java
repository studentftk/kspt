/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.api;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.Comment;
import server.io.JSONHelper;
import server.logic.CommentDAO;

/**
 *
 * @author oglandx
 * created on 22.04.2015 by oglandx
 */
public class ManipCommentsApi implements ApiMethod{
    
    public static final class Operations{
        public static final String Add = "add";
        public static final String Delete = "del";
    }
    
    @Override
    public ApiMethod.ApiAnswer execute(Map<String, String> params) {
        try {
            if(params.get("op") != null) switch(params.get("op")){
                case Operations.Add:
                    if( params.get("type") != null && 
                        params.get("entityId") != null &&
                        params.get("idVkUser") != null &&
                        params.get("head") != null &&
                        params.get("text") != null){
                        
                        int entityType = 0;
                        switch(params.get("type")){
                            case Comment.EntityTypesStr.Places:
                                entityType = Comment.EntityTypes.Places;
                                break;
                            case Comment.EntityTypesStr.News:
                                entityType = Comment.EntityTypes.News;
                                break;
                            default:
                                throw new Exception("Bad parameter type");
                        }
                        Long entityId = Long.parseLong(params.get("entityId"));
                        Long idVkUser = Long.parseLong(params.get("idVkUser"));
                        Long prevComment = 0L;
                        if(params.get("previousComment") != null){
                            prevComment = Long.parseLong(
                                    params.get("previousComment"));
                        }
                        CommentDAO.AddComment(entityType, entityId, idVkUser, 
                            prevComment, params.get("head"), params.get("text"));
                    }
                    break;
                case Operations.Delete:
                    Long commentId = Long.parseLong(params.get("id"));
                    CommentDAO.DeleteComment(commentId);
                    break;
                default:
                    throw new Exception(
                            "Unsupported operation: " + params.get("op"));
            }
            return new ApiAnswer(HttpCode.OK, "");
        } catch (Exception ex) {
            String answer = JSONHelper.toJSON(ex);
            Logger.getLogger(VKApi.class.getName()).log(Level.SEVERE, null, ex);
            return new ApiMethod.ApiAnswer(HttpCode.ERROR, answer);
        }    
    }
}
