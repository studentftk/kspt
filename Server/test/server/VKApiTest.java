/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.Map;
import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
     * Test of getUserData method, of class VKApi.
     */
    @Test
    public void testGetUserData() throws Exception {
        System.out.println("getUserData");
        DBManager db = new DBManager();
        String id = "12099297";
        SocialApi instance = new VKApi(db);
        Map<String, String> result = instance.getUserData(id);
        if (result.get("Name")==null
             || result.get("Surname")== null
             || result.get("Photo")==null)
            fail();
        System.out.println(result.get("Name")+":"+result.get("Surname")+":"+result.get("Photo"));
    }
    
}
