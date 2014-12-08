/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.logic;

import server.DbConnectionFactory;

/**
 *
 * @author llama
 */
public class Message {
    
    private static final String table = "messages";
    
    public static void send(DbConnectionFactory dbConnectionFactory, String senderToken, int idDestination, String message){
        
    }
    
}
