package sigmascheduler.engine.data;

import java.util.HashMap;
import java.util.List;
import java.util.Date;

public class Event {

	private HashMap<Date,List<User>> voteDates;

	private User manager;

	private List<User> member;

	private List<Comment> comments;

	private EventState state;

	private boolean allowMultipleVotes;

	private Comment comment;

	private User user;

	private Event event;

	private EventState eventState;

	public int getVotes(Date date) {
		return 0;
	}

	public Date getTopVote() {
		return null;
	}

	public User getManager() {
		return null;
	}

	public List<User> getMember() {
		return null;
	}

	public List<Comment> getComments() {
		return null;
	}

}
