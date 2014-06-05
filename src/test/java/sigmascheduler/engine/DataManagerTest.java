/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sigmascheduler.engine;

import java.nio.charset.Charset;
import java.util.List;
import static java.util.Objects.hash;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sigmascheduler.engine.data.User;

/**
 * JUnit-Test DataManager
 * @author Andreas Vogt
 */
public class DataManagerTest {
    private User user = new User();
    private SigmaSchedulerSession session = new SigmaSchedulerSession();
    private UserManager usm = session.getUserManager();
    private DataManager data = session.getDataManager();
    public DataManagerTest() {
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
     * Getter-Methoden testet man nicht!
     */
//    @Test
//    public void testGet() {
//    }

    /**
     * Test of save method, of class DataManager.
     */
    @Test
    public void testSave() throws Exception {
        user.setName("Test2Name");
        user.setEmail("Test2Email@Test.at");
        String passwordt = "test2";
        byte[] password;
        password = passwordt.getBytes(Charset.forName("UTF-8"));
        user.setPassword(password);
        
        data.save(user);
        assertEquals(user.getName(),"Test2Name");
        assertEquals(user.getEmail(),"Test2Email@Test.at");
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of delete method, of class DataManager.
     */
    @Test
    public void testDelete() throws Exception {

        data.delete(user);
        
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(null,user.getName());
    }

    /**
     * Test of buildSession method, of class DataManager.
     */
    @Test
    public void testBuildSession() throws Exception {
        System.out.println("buildSession");
        DataManager instance = null;
        Session expResult = null;
        Session result = instance.buildSession();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of executeQuery method, of class DataManager.
     */
    @Test
    public void testExecuteQuery() throws Exception {
        System.out.println("executeQuery");
        String queryName = "";
        Object param = null;
        DataManager instance = null;
        List expResult = null;
        List result = instance.executeQuery(queryName, param);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
