/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import utils.QueryHelper;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
    
    @Test
    public void testParse() throws ParseException{
        System.out.println("parseTest");
        String answ = "[{\"message\":\"Привет!\",\"id\":15,\"source\":45,\"sendTime\":\"2014-12-08 22:19:55.0\",\"destination\":736880},{\"message\":\"Привет!\",\"id\":16,\"source\":45,\"sendTime\":\"2014-12-08 22:20:02.0\",\"destination\":736880},{\"message\":\"Привет!\",\"id\":17,\"source\":45,\"sendTime\":\"2014-12-08 22:27:04.0\",\"destination\":736880},{\"message\":\"Привет :)!\",\"id\":18,\"source\":736880,\"sendTime\":\"2014-12-08 22:27:39.0\",\"destination\":45},{\"message\":\"Привет :)!\",\"id\":19,\"source\":736880,\"sendTime\":\"2014-12-08 22:27:49.0\",\"destination\":45},{\"message\":\"Хайло\",\"id\":20,\"source\":736880,\"sendTime\":\"2014-12-08 23:25:41.0\",\"destination\":45}]";
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray)jsonParser.parse(answ);
    }
    
}
