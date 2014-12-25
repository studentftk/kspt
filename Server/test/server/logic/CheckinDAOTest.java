/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.logic;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import server.entity.Checkin;
import server.io.JSONHelper;

/**
 *
 * @author llama
 */
public class CheckinDAOTest {
    
    public CheckinDAOTest() {
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

    @Test
    public void testGetLastCheckins() {
        System.out.println("getLastCheckins");
        long idUser = 45L;
        int count = 10;
        List<Checkin> result = CheckinDAO.getLastCheckins(idUser, count);
        System.out.println(JSONHelper.toJSON(result));
    }

    @Test
    public void testCheckin() {
        System.out.println("checkin");
        Checkin checkin = new Checkin()
                .setIdPlace(1L)
                .setIdUser(45L);
        CheckinDAO.checkin(checkin);
    }
    
}
