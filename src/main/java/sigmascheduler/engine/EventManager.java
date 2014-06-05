package sigmascheduler.engine;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import sigmascheduler.engine.data.Comment;
import sigmascheduler.engine.data.Event;
import sigmascheduler.engine.data.EventState;
import sigmascheduler.engine.data.User;
import sigmascheduler.engine.data.VoteDate;

/**
 * Manages the interaction with the Event class
 * @author Dominik Scholz
 * @version 0.3
 */
public class EventManager {
    
    private SigmaSchedulerSession session;
    private DataManager dataManager;
    
    public EventManager(SigmaSchedulerSession session) {
        this.session = session;
        dataManager = session.getDataManager();
    }
    
    /**
     * Updates an event
     * @param name
     * @param description
     * @param allowMultipleVotes
     * @param dates
     * @param member
     * @param event
     * @return the updated event (useless bcause given as param)
     * @throws SigmaSchedulerException 
     */
    public Event updateEvent(String name, String description, boolean allowMultipleVotes, List<Date> dates, Set<User> member, Event event) throws SigmaSchedulerException {
        //Creating new event
        event.setManager(session.getUser());
        event.setName(name);
        event.setDescription(description);
        
        event.setAllowMultipleVotes(allowMultipleVotes);
        event.setMember(member);
        
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
    
    /**
     * Creates a new event
     * @param name
     * @param description
     * @param allowMultipleVotes
     * @param dates
     * @param member
     * @return the new event
     * @throws SigmaSchedulerException 
     */
    public Event createEvent(String name, String description, boolean allowMultipleVotes, List<Date> dates, Set<User> member) throws SigmaSchedulerException {
        //Creating new event
        Event event = new Event();
        event.setManager(session.getUser());
        event.setName(name);
        event.setDescription(description);
        event.setState(EventState.UNPUBLISHED);
        
        event.setCreateDate(new Date());
        event.setAllowMultipleVotes(allowMultipleVotes);
        event.setComments(new HashSet<Comment>());
        event.setMember(member);
        
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
    
    /**
     * Returns all events managed by the given user
     * @param manager the manager of the events
     * @return a list of all events managed by the given user
     * @throws SigmaSchedulerException 
     */
    public List<Event> getManagedEvents(User manager) throws SigmaSchedulerException {
        return dataManager.executeQuery("getManagedEvents",manager);
    }

    /**
     * Deletes an event
     * @param event
     * @throws SigmaSchedulerException 
     */
    public void delete(Event event) throws SigmaSchedulerException {
        dataManager.delete(event);
    }

    /**
     * Publishes an event
     * @param event
     * @throws SigmaSchedulerException 
     */
    public void publishEvent(Event event) throws SigmaSchedulerException {
        event.setState(EventState.VOTEABLE);
        dataManager.save(event);
    }
    
}
