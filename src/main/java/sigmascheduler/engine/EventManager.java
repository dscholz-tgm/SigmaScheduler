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
 * @version 0.2
 */
public class EventManager {
    
    private SigmaSchedulerSession session;
    private DataManager dataManager;
    
    public EventManager(SigmaSchedulerSession session) {
        this.session = session;
        dataManager = session.getDataManager();
    }
    
    public Event createEvent(String name, String description, boolean allowMultipleVotes, List<Date> dates, Set<User> member, Event event) throws SigmaSchedulerException {
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
    
    public List<Event> getManagedEvents(User manager) throws SigmaSchedulerException {
        return dataManager.executeQuery("getManagedEvents",manager);
    }

    public void delete(Event event) throws SigmaSchedulerException {
        dataManager.delete(event);
    }
    
}
