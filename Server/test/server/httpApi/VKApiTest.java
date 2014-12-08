/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.httpApi;

import server.httpApi.VKApi;
import java.util.Map;
import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import server.DbConnectionFactory;
import static org.junit.Assert.*;
import server.logic.User;

/**
 *
 * @author llama
 */
public class VKApiTest {
    
    public VKApiTest() {
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
     * Test of putUserDataFromJson method, of class VKApi.
     */
    @Test
    public void testGetUserData() throws Exception {
        System.out.println("getUserData");
        DbConnectionFactory db = new DbConnectionFactory();
        int id = 12099297;
        String token = "";
        VKApi instance = new VKApi(db);
        User result;
        result = instance.getUserData(new VKApi.AuthInfo(id, token));
        result.save(db);
        System.out.println(result.asJSONObject().toJSONString());
    }
    
}
