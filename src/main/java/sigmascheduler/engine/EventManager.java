package sigmascheduler.engine;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import sigmascheduler.engine.data.Comment;
import sigmascheduler.engine.data.Event;
import sigmascheduler.engine.data.EventState;
import sigmascheduler.engine.data.User;
import sigmascheduler.engine.data.VoteDate;

/**
 * Manages the interaction with the Event class
 * @author Dominik Scholz
 * @version 0.2
 */
public class EventManager {
    
    private SigmaSchedulerSession session;
    private DataManager dataManager;
    
    public EventManager(SigmaSchedulerSession session) {
        this.session = session;
        dataManager = session.getDataManager();
    }
    
    public Event createEvent(String name, String description, boolean allowMultipleVotes, List<Date> dates, Event event) throws SigmaSchedulerException {
        //Creating new event
        event.setManager(session.getUser());
        event.setName(name);
        event.setDescription(description);
        
        event.setAllowMultipleVotes(allowMultipleVotes);
        
        //Parsing vote dates
        Set<VoteDate> voteDates = new HashSet<VoteDate>();
        VoteDate voteDate;
        for(Date date : dates) {
            voteDate = new VoteDate();
            voteDate.setDate(date);
            voteDate.setVoter(new HashSet<User>());
            voteDates.add(voteDate);
        }
        event.setVoteDates(voteDates);
        
        //Saving the new event
        dataManager.save(event);
        
        return event;
    }
    
    public Event createEvent(String name, String description, boolean allowMultipleVotes, List<Date> dates) throws SigmaSchedulerException {
        //Creating new event
        Event event = new Event();
        event.setManager(session.getUser());
        event.setName(name);
        event.setDescription(description);
//        event.setState(EventState.UNPUBLISHED);
        //teststate, remove later!
        Random rand = new Random();
        event.setState(EventState.values()[rand.nextInt(EventState.values().length)]);
        
        event.setCreateDate(new Date());
        event.setAllowMultipleVotes(allowMultipleVotes);
        event.setComments(new HashSet<Comment>());
        
        //Parsing vote dates
        Set<VoteDate> voteDates = new HashSet<VoteDate>();
        VoteDate voteDate;
        for(Date date : dates) {
            voteDate = new VoteDate();
            voteDate.setDate(date);
            voteDate.setVoter(new HashSet<User>());
            voteDates.add(voteDate);
        }
        event.setVoteDates(voteDates);
        
        //Saving the new event
        dataManager.save(event);
        
        return event;
    }
    
    public List<Event> getManagedEvents(User manager) throws SigmaSchedulerException {
        return dataManager.executeQuery("getManagedEvents",manager);
    }
    
//    public User registerUser(String name, String email, byte[] password) throws SigmaSchedulerException {
//        //Check if user is alreay in the database
//        Query query = DataManager.get().getNamedQuery("getUser");
//        query.setParameter("name", name);
//        List<User> result = query.list();
//        if(!result.isEmpty()) throw new SigmaSchedulerException("Username already registerd");
//        
//        //Creating new user
//        user = new User();
//        user.setName(name);
//        user.setEmail(email);
//        user.setPassword(hash(password));
//        
//        //Saving new user
//        DataManager.get().save(user);
//        
//        return user;
//    }
//
//    public User loginUser(String name, byte[] password) throws SigmaSchedulerException {
//        if(name.equals("")) throw new SigmaSchedulerException("Empty Name!");
//        if(password.length == 0) throw new SigmaSchedulerException("Empty Password!");
//        
//        Query query = DataManager.get().getNamedQuery("getUser");
//        query.setParameter("name", name);
//        List<User> result = query.list();
//        
//        //Check if user is registerd
//        if(result.isEmpty()) throw new SigmaSchedulerException("register","Username not registerd, register user?");
//        
//        //Check if the password is right
//        if(!Arrays.equals(result.get(0).getPassword(), hash(password))) throw new SigmaSchedulerException("forgotpassword","Wrong password");
//        
//        user = result.get(0);
//        
//        //Setting the user for the current session
//        SigmaSchedulerSession.get().setUser(user);
//        return user;
//    }
//
//    public List<User> searchUser(String name) {
////        hql query to search all users with regular expression "name"
//        return null;
//    }
//    
//    private byte[] hash (byte[] password) throws SigmaSchedulerException {
//        MessageDigest messageDigest = null;
//        try {
//            messageDigest = MessageDigest.getInstance("SHA-1");
//        } catch (NoSuchAlgorithmException ex) {
//            throw new SigmaSchedulerException("Hash Problem, should never ever happen :/");
//        }
//        //Salt hash maybe?
//        return messageDigest.digest(password);
//    }
//    
//            
//    public Image getAvatar() {
//        MessageDigest messageDigest = null;
//        try {
//            messageDigest = MessageDigest.getInstance("MD5");
//        } catch (NoSuchAlgorithmException ex) {
//        }
//        byte[] hashAr = messageDigest.digest(user.getEmail().getBytes(Charset.forName("UTF-8")));
//        StringBuilder sb = new StringBuilder();
//        for (byte b : hashAr) sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));
//        return new Image(null, new ExternalResource ("http://www.gravatar.com/avatar/"+sb.toString()+".jpg?d=identicon&s=240"));
//    }

    public void delete(Event event) throws SigmaSchedulerException {
        dataManager.delete(event);
    }
    
}
