/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigmascheduler.engine;

import com.vaadin.ui.Image;
import java.nio.charset.Charset;
import java.util.List;
import static java.util.Objects.hash;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sigmascheduler.engine.data.User;

/**
 * JUnit-Test Class for UserManager
 * @author Andreas Vogt
 */
public class UserManagerTest {
    
    private SigmaSchedulerSession session = new SigmaSchedulerSession();
    private UserManager usm = session.getUserManager();
    private DataManager data = session.getDataManager();
    public UserManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of registerUser method, of class UserManager.
     */
    @Test
    public void testRegisterUser() throws SigmaSchedulerException {
        User result;
        String name = "testUser";
        String email = "testEmail@test.at";
        String passwordt = "test";
        byte[] password = passwordt.getBytes(Charset.forName("UTF-8"));
        result = usm.registerUser(name, email, password);
        assertEquals(result.getName(), name);
        assertEquals(result.getEmail(),email);
        //assertEquals(result.getPassword(),password);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of loginUser method, of class UserManager.
     */
    @Test
    public void testLoginUser() throws Exception {
        User result;
        System.out.println("loginUser");
        String name = "testUser";
        String passwordt = "test";
        byte[] password = passwordt.getBytes(Charset.forName("UTF-8"));
        result = usm.loginUser(name, password);
        assertEquals(result.getName(), name);
        assertEquals(result.getEmail(),"testEmail@test.at");
        //assertEquals(result.getPassword(),password);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    

    /**
     * Test of searchUser method, of class UserManager.
     */
//    @Test
//    public void testSearchUser() {
//        System.out.println("searchUser");
//        String name = "testUser";
//        List<User> result = usm.searchUser(name);
//        result.get(0).getName();
//        assertEquals(name, result.get(0).getName());
//        // TODO review the generated test code and remove the default call to fail.
//    }

    /**
     * Test of getAvatar method, of class UserManager.
     */
//    @Test
//    public void testGetAvatar() {
//        System.out.println("getAvatar");
//        UserManager instance = null;
//        Image expResult = null;
//        Image result = instance.getAvatar();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
