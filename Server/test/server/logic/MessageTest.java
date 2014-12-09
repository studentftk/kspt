/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.logic;

import java.sql.Time;
import java.sql.Timestamp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import server.DbConnectionFactory;

/**
 *
 * @author llama
 */
public class MessageTest {
    
    public MessageTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of send method, of class Message.
     */
    @Test
    public void testSend() throws Exception {
        System.out.println("send");
        DbConnectionFactory connectionFactory = new DbConnectionFactory();
        Message msg = new Message(736880, 45, "Привет :)!");
        msg.send(connectionFactory);
        System.out.println("OK");
    }

    /**
     * Test of getDestinationMessages method, of class Message.
     */
    @Test
    public void testGetDestinationMessages() throws Exception {
        System.out.println("getDestinationMessages");
        long destination = 736880L;
        Timestamp from = Timestamp.valueOf("1992-1-1 15:30:45");
        JSONArray result = Message.getDestinationMessages(new DbConnectionFactory(), destination, from);
        System.out.println(result.toJSONString());
    }
    
    /**
     * Test of getDestinationMessages method, of class Message.
     */
    @Test
    public void testGetAllMessages() throws Exception {
        System.out.println("getAllMessages");
        long destination = 736880L;
        Timestamp from = Timestamp.valueOf("1992-1-1 15:30:45");
        JSONArray result = Message.getAllMessages(new DbConnectionFactory(), destination, from);
        System.out.println(result.toJSONString());
    }
    
}
