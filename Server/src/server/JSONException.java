/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import org.json.simple.JSONObject;

/**
 *
 * @author llama
 */
public class JSONException {
    private JSONException(){};
    
    public static String toJSON(Exception e){
        JSONObject json = new JSONObject();
        json.put("exception", e.getClass().getName());
        json.put("message", e.getMessage());
        return json.toJSONString();
    }
}
