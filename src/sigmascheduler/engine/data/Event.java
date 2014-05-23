package sigmascheduler.engine.data;

import java.util.HashMap;
import java.util.List;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Entity;

@Entity
public class Event {
	
	@Column
	private HashMap<Date, List<User>> voteDates;
	
	@Column
	private User manager;
	
	@Column
	private List<User> member;
	
	@Column
	private List<Comment> comments;
	
	@Column
	private EventState state;
	
	@Column
	private boolean allowMultipleVotes;
	
	@ManyToOne
	private Comment comment;
	
	@ManyToMany
	private User user;

	//@Column
	//private Event event;
	
	@Column
	private EventState eventState;

	public HashMap<Date, List<User>> getVoteDates() {
		return voteDates;
	}

	public User getManager() {
		return manager;
	}

	public List<User> getMember() {
		return member;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public EventState getState() {
		return state;
	}

	public boolean isAllowMultipleVotes() {
		return allowMultipleVotes;
	}

	public Comment getComment() {
		return comment;
	}

	public User getUser() {
		return user;
	}

	//public Event getEvent() {
		//return event;
	//}

	public EventState getEventState() {
		return eventState;
	}

	public void setVoteDates(HashMap<Date, List<User>> voteDates) {
		this.voteDates = voteDates;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public void setMember(List<User> member) {
		this.member = member;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setState(EventState state) {
		this.state = state;
	}

	public void setAllowMultipleVotes(boolean allowMultipleVotes) {
		this.allowMultipleVotes = allowMultipleVotes;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public void setUser(User user) {
		this.user = user;
	}

	//public void setEvent(Event event) {
	//	this.event = event;
	//}

	public void setEventState(EventState eventState) {
		this.eventState = eventState;
	}

}
