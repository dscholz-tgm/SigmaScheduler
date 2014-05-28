package sigmascheduler.engine.data;

import java.util.List;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Entity;
/**
 * Event Mapping
 * 
 * @author Vogt , Kodras
 * 
 */
@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private Map<Date, List<User>> voteDates;
	
	@Column
	private User manager;
	
	@Column
	private List<User> member;
	
	@Column
	private List<Comment> comments;
	
	@Column
	@Enumerated
	private EventState state;
	
	@Column
	private boolean allowMultipleVotes;
	
	@ManyToOne
	private Comment comment;
	
	//Getter
	public Map<Date, List<User>> getVoteDates() {
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

	public int getEid() {
		return id;
	}
	
	//Setter
	
	public void setEid(int id) {
		this.id = id;
	}

	public void setVoteDates(Map<Date, List<User>> voteDates) {
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
}
