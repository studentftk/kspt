/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.Collection;
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
public class QueryHelperTest {
    
    public QueryHelperTest() {
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
     * Test of mapToSQLInsert method, of class QueryHelper.
     */
    @Test
    public void testMapToSQLInsert() {
        System.out.println("mapToSQLInsert");
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "Alex");
        String tableName = "users";
        String expResult = "INSERT INTO users (id,name) VALUES (\"1\",\"Alex\");";
        String result = QueryHelper.mapToSQLInsert(map, tableName);
        System.out.println("\t"+result);
        assertEquals(expResult, result);
    }

    /**
     * Test of joinPairs method, of class QueryHelper.
     */
    @Test
    public void testJoinPairs() {
        System.out.println("joinPairs");
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "Alex");
        String expResult = "id=\"1\",name=\"Alex\"";
        String result = QueryHelper.joinPairs(map);
        System.out.println("\t"+result);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testMapToSQLUpdate(){
        System.out.println("mapToSQLUpdate");
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "Alex");
        String tableName = "users";
        String expResult = "UPDATE users SET id=\"1\",name=\"Alex\";";
        String result = QueryHelper.mapToSQLUpdate(map, tableName);
        System.out.println("\t"+result);
        assertEquals(expResult, result);
    }
    
}
