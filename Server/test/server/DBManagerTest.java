/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
public class DBManagerTest {
    
    public DBManagerTest() {
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


    
//    @Test
//    public void testUpdate() throws Exception {
//        HashMap map = new HashMap();
//        map.put("ID", "1");
//        map.put("Name", "Кто-то");
//        DBManager db = new DBManager();
//        db.updateOrInsertUser(map);
//    }
    
    @Test
    public void testGetUser() throws SQLException{
        System.out.println("get");
        DBManager db = new DBManager();
        Map smt = db.getUser(155, "vk");
        System.out.println(smt.isEmpty());
        System.out.println(smt.get("Name"));
    }

    @Test
    public void testInsertOrUpdateUser() throws SQLException{
        System.out.println("insertOrUpdate");
        DBManager db = new DBManager();
        Map usr = new HashMap();
        usr.put("Social_id", "155");
        usr.put("Social_type", "vk");
        usr.put("Name", "Toster");
        db.updateOrInsertUser(usr);
    }

}
