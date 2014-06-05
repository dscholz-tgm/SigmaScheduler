/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sigmascheduler.engine;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sigmascheduler.engine.data.Event;
import sigmascheduler.engine.data.User;
import sigmascheduler.engine.data.VoteDate;

/**
 * JUnit Test for EventManager
 * @author Andreas Vogt
 */
public class EventManagerTest {
    
    private User result;
    private SigmaSchedulerSession session = new SigmaSchedulerSession();
    private UserManager usm = session.getUserManager();
    private DataManager data = session.getDataManager();
    private EventManager eventm = session.getEventManager();
private Event event = new Event() ;
    public EventManagerTest() {
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
     * Test of createEvent method, of class EventManager.
     */
    @Test
    public void testCreateEvent() throws Exception {
        Set<User> member;
        member = new HashSet<User>();
        User test1;
        User test2;
        test1 = new User();
        test1.setName("test1");
        test2 = new User();
        test2.setName("test2");
        member.add(test1);
        member.add(test2);
        System.out.println("createEvent");
        String name = "EventTest";
        String description = "Test";
        boolean allowMultipleVotes = false;
        Date dateTest1 = new Date();
        Date dateTest2 = new Date();
        List<Date> dates = new ArrayList<Date>();
        dates.add(dateTest1);
        dates.add(dateTest2);
        event = eventm.createEvent(name, description, allowMultipleVotes, dates,member);
        assertEquals(name, event.getName());
        assertEquals(description, event.getDescription());
        String name1 = "UpdateTest";
        String desc1 = "Update";
        eventm.createEvent(name1, desc1, allowMultipleVotes, dates,member,event);
        assertEquals(name1, event.getName());
        assertEquals(desc1, event.getDescription());
        // TODO review the generated test code and remove the default call to fail.
    }

//    /**
//     * Test of getManagedEvents method, of class EventManager.
//     */
//    @Test
//    public void testGetManagedEvents() throws Exception {
//        System.out.println("getManagedEvents");
//        User manager = null;
//        EventManager instance = null;
//        List<Event> expResult = null;
//        List<Event> result = instance.getManagedEvents(manager);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
    /**
     * Test of delete method, of class EventManager.
     */
//    @Test
//    public void getManagedEvents() throws Exception {
//        eventm.delete(event);
//        assertEquals(null, event.getName());
//        
//    }
    @Test
    public void testDelete() throws Exception {
        eventm.delete(event);
        assertEquals(null, event.getName());
        
    }
    
    
}
