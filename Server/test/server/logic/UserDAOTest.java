/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import server.entity.User;

/**
 *
 * @author llama
 */
public class UserDAOTest {
    
    public UserDAOTest() {
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
     * Test of getById method, of class UserDAO.
     */
    @Test
    public void testGetUserById() {
        System.out.println("getUserById");
        long id = 45L;
        User expResult = null;
        User result = UserDAO.getById(id);
        System.out.println(result.asJSON().toJSONString());
    }

    /**
     * Test of getByToken method, of class UserDAO.
     */
    @Test
    public void testGetUserByToken() {
        System.out.println("getUserByToken");
        String token = "";
        User expResult = null;
        User result = UserDAO.getByToken(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBySocial method, of class UserDAO.
     */
    @Test
    public void testGetUserBySocial() {
        System.out.println("getUserBySocial");
        String socialType = "";
        long socialId = 0L;
        User expResult = null;
        User result = UserDAO.getBySocial(socialType, socialId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class UserDAO.
     */
    @Test
    public void testSeve() {
        System.out.println("save");
        User user = new User()
                .setName("Test_ref")
                .setSocialId(1L)
                .setSocialType("vk")
                .setSocialToken("123");
        UserDAO.save(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
