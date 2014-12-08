/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.logic;

import java.sql.Connection;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import server.DbConnectionFactory;
import utils.QueryHelper;

/**
 *
 * @author llama
 */
public class UserTest {
    
    public UserTest() {
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
     * Test of getUser method, of class User.
     */
    @Test
    public void testGetUser() throws Exception {
        System.out.println("getUser");
        DbConnectionFactory factory = new DbConnectionFactory();
        Integer id = 736881;
        User result = User.getUser(factory, id);
        System.out.println(result.asJSONObject().toJSONString());
    }


    /**
     * Test of getIdByToken method, of class User.
     */
    @Test
    public void testGetIdByToken() throws Exception {
        System.out.println("getIdByToken");
        DbConnectionFactory factory = new DbConnectionFactory();
        String token = null;
        int expResult = 736881;
        int result = User.getIdByToken(factory, token);
        System.out.println(result);
        assertEquals(expResult, result);
    }


    /**
     * Test of save method, of class User.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        DbConnectionFactory factory = new DbConnectionFactory();
        User user = new User();
        user.id = 45;
        user.socialId = 5644;
        user.socialType ="vko";
        user.socialToken = "adsfa";
        System.out.println(QueryHelper.mapToSQLInsert(user.asJSONObject(), "users"));
        user.save(factory);
        
    }

    /**
     * Test of getIdBySocial method, of class User.
     */
    @Test
    public void testGetIdBySocial() throws Exception {
        System.out.println("getIdBySocial");
        DbConnectionFactory factory = new DbConnectionFactory();
        int socialId = 12099297;
        String socialType = "vk";
        int expResult = 736881;
        int result = User.getIdBySocial(factory, socialId, socialType);
        System.out.println(result);
        assertEquals(expResult, result);
    }
    
}
