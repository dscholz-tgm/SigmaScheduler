package sigmascheduler.engine;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import sigmascheduler.engine.data.Event;
import sigmascheduler.engine.data.Comment;
import sigmascheduler.engine.data.User;
import sigmascheduler.engine.data.Notification;

public class DataManager {

	private SessionFactory factory;

	private Session session;

	private Event event;

	private Comment comment;

	private User user;

	private Notification notification;

	public void save(Object object) {

	}
}