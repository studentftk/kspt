package server.logic;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import server.entity.Place;
import server.io.JSONHelper;

public class PlaceDAOTest {
    
    public PlaceDAOTest() {
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
     * Test of getPlacesByType method, of class PlaceDAO.
     */
    @Test
    public void testGetPlacesByType() {
        System.out.println("getPlacesByType");
        String type = "teachCorp";
        List<Place> result = PlaceDAO.getPlacesByType(type);
        System.out.println(JSONHelper.toJSON(result));
    }
    
}
