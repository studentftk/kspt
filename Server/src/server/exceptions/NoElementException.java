/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.exceptions;

import java.util.NoSuchElementException;
import org.json.simple.JSONObject;

/**
 *
 * @author llama
 */
public class NoElementException extends NoSuchElementException{
    
    static final String name = "NoElementException";
    
    public NoElementException(String msg){
        super(msg);
    }
    
    public String toJSON(){
        JSONObject json = new JSONObject();
        json.put("exception", name);
        json.put("message", getMessage());
        return json.toJSONString();
    }
   
}
