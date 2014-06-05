package sigmascheduler.engine.data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

/**
 * Event which should take place at some point, 
 * our quest is the epic journey to find that point
 * @author Andreas Vogt, Dominik Kodras, Dominik Scholz, Osman Oezsoy
 * @version 0.2
 */
@Entity
@NamedQuery(
    //Returns all managed events from a user
    name="getManagedEvents",
    query="from Event e where e.manager = :id")
public class Event implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    
    private String name;
    
    private String description;
        
    @Enumerated(EnumType.STRING)
    private EventState state;

    private User manager;
    
    @Temporal(TemporalType.DATE)
    private Date createDate;

    private boolean allowMultipleVotes;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> member;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<VoteDate> voteDates;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Comment> comments;
    
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public EventState getState() { return state; }
    public User getManager() { return manager; }
    public Date getCreateDate() { return createDate; }
    public boolean isAllowMultipleVotes() { return allowMultipleVotes; }
    public Set<User> getMember() { return member; }
    public Set<Comment> getComments() { return comments; }
    public Set<VoteDate> getVoteDates() { return voteDates; }
    
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setState(EventState state) { this.state = state; }
    public void setManager(User manager) { this.manager = manager; }
    public void setCreateDate(Date createDate) { this.createDate = createDate; }
    public void setAllowMultipleVotes(boolean allowMultipleVotes) { this.allowMultipleVotes = allowMultipleVotes;}
    public void setMember(Set<User> member) { this.member = member; }
    public void setComments(Set<Comment> kommentare) {  this.comments = kommentare; }
    public void setVoteDates(Set<VoteDate> voteDates) { this.voteDates = voteDates; }
}
