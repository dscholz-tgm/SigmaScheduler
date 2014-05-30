package sigmascheduler.engine.data;

import java.util.*;

import javax.persistence.*;

/**
 * Event Mapping
 * 
 * @author Vogt , Kodras
 * 
 */
@Entity
@Table(name = "event")
public class Event {
	@Id
	@GeneratedValue
	private int id;
	
	@OneToMany
	private List<VoteDate> voteDates;
	
	private User manager;
	
	@OneToMany
	private List<User> member;
	
	@OneToMany
	private List<Comment> kommentare;
	
	@Enumerated(EnumType.STRING)
	private EventState state;
		
	private boolean allowMultipleVotes;
	
	private Comment kommentar;
	
	//Getter
	
	public int getId() {
		return id;
	}

	public EventState getState() {
		return state;
	}
	/*
	public Map<Date, List<User>> getVoteDates() {
		return voteDates;
	}
	*/
	public User getManager() {
		return manager;
	}

	public List<User> getMember() {
		return member;
	}
	
	public List<Comment> getKommentare() {
		return kommentare;
	}
	
	@OneToMany
	public Comment getKommentar() {
		return kommentar;
	}
		
	public boolean isAllowMultipleVotes() {
		return allowMultipleVotes;
	}
	
	
	//Setter
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setState(EventState state) {
		this.state = state;
	}
	/*
	public void setVoteDates(Map<Date, List<User>> voteDates) {
		this.voteDates = voteDates;
	}
	*/
	public void setManager(User manager) {
		this.manager = manager;
	}

	public void setMember(List<User> member) {
		this.member = member;
	}
	
	public void setKommentare(List<Comment> kommentare) {
		this.kommentare = kommentare;
	}

	public void setAllowMultipleVotes(boolean allowMultipleVotes) {
		this.allowMultipleVotes = allowMultipleVotes;
	}
	
	public void setKommentar(Comment kommentar) {
		this.kommentar = kommentar;
	}
}
