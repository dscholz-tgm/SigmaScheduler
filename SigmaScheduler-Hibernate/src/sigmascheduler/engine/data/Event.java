package sigmascheduler.engine.data;

import java.util.*;

import javax.persistence.*;

/**
 * Mappen der Klasse Event mittels Hibernate-Annotations
 * 
 * @author Kodras, Oezsoy, Vogt
 */
@NamedQuery(name = "getAllEvents", query = "FROM Event")
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

	// Getter-Methoden

	public int getId() {
		return id;
	}

	public EventState getState() {
		return state;
	}

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

	public List<VoteDate> getVoteDates() {
		return voteDates;
	}

	// Setter-Methoden

	public void setId(int id) {
		this.id = id;
	}

	public void setState(EventState state) {
		this.state = state;
	}

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

	public void setVoteDates(List<VoteDate> voteDates) {
		this.voteDates = voteDates;
	}
}