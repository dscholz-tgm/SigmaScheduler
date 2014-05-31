package sigmascheduler.testing;

import static org.junit.Assert.*;

import java.nio.charset.*;
import java.text.*;
import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.junit.*;

import sigmascheduler.engine.data.*;

/**
 * Testen der Hibernate-Mapping mittels JUnit-Test-Cases
 * 
 * @author Oezsoy, Vogt
 */
public class SigmaSchedulerMappingTest {

	private static final SessionFactory sessionFactory;

	private Session session;

	private Transaction transaction;

	static {
		try {
			// Erzeugen der SessionFactory aus der Datei hibernate.cfg.xml
			sessionFactory = new AnnotationConfiguration().configure()
					.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	@Before
	public void before() {
		this.session = sessionFactory.getCurrentSession();
		this.transaction = session.beginTransaction();
	}

	@Test
	public void testUser() {
		byte[] password = "hallo123".getBytes(Charset.forName("UTF-8"));

		User u = new User();
		u.setId(1);
		u.setName("Maximilian");
		u.setEmail("max123@hotmail.com");
		u.setPassword(password);

		this.session.save(u);
		this.transaction.commit();

		Session s = sessionFactory.openSession();

		org.hibernate.Query namedQuery = s.getNamedQuery("getAllUser");

		List<User> list = namedQuery.list();

		assertEquals(u.getId(), list.get(0).getId());
		assertEquals(u.getName(), list.get(0).getName());
		assertEquals(u.getEmail(), list.get(0).getEmail());
		assertArrayEquals(u.getPassword(), list.get(0).getPassword());
	}

	@Test
	public void testComment() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String date = "16-04-2014 10:22:53";

		Comment c = new Comment();
		c.setId(1);

		try {
			c.setCreateDate(sdf.parse(date));
		} catch (ParseException e) {
			System.out
					.println("Beim Parsen des Datums ist ein Fehler aufgetreten!");
		}

		c.setText("Dies ist ein Kommentar");

		this.session.save(c);
		this.transaction.commit();

		Session s = sessionFactory.openSession();

		org.hibernate.Query namedQuery = s.getNamedQuery("getAllComments");

		List<Comment> list = namedQuery.list();

		assertEquals(c.getId(), list.get(0).getId());
		assertEquals(c.getCreateDate(), list.get(0).getCreateDate());
		assertEquals(c.getText(), list.get(0).getText());
	}

	@Test
	public void testEvent() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String date = "16-04-2014 10:22:53";

		Comment c = new Comment();
		c.setId(1);

		try {
			c.setCreateDate(sdf.parse(date));
		} catch (ParseException e) {
			System.out
					.println("Beim Parsen des Datums ist ein Fehler aufgetreten!");
		}

		c.setText("Dies ist ein Kommentar zum Event");

		List<Comment> cl = new ArrayList<Comment>();
		cl.add(c);

		byte[] password = "hallo123".getBytes(Charset.forName("UTF-8"));

		User u = new User();
		u.setId(1);
		u.setName("Maximilian");
		u.setEmail("max123@hotmail.com");
		u.setPassword(password);

		List<User> ul = new ArrayList<User>();
		ul.add(u);

		VoteDate vd = new VoteDate();
		vd.setId(1);

		try {
			vd.setDate(sdf.parse(date));
		} catch (ParseException e) {
			System.out
					.println("Beim Parsen des Datums ist ein Fehler aufgetreten!");
		}

		vd.setBenutzer(ul);

		List<VoteDate> vdl = new ArrayList<VoteDate>();
		vdl.add(vd);

		Event ev = new Event();
		ev.setId(1);
		ev.setState(EventState.UNPUBLISHED);
		ev.setKommentar(c);
		ev.setManager(u);

		this.session.save(ev);
		this.transaction.commit();

		Session s = sessionFactory.openSession();

		org.hibernate.Query namedQuery = s.getNamedQuery("getAllEvents");

		List<Event> list = namedQuery.list();

		assertEquals(ev.getId(), list.get(0).getId());
		assertEquals(ev.getKommentar().getText(), list.get(0).getKommentar()
				.getText());
		assertEquals(ev.getState(), list.get(0).getState());
		assertEquals(ev.getManager().getId(), list.get(0).getManager().getId());
	}

	@Test
	public void testVoteDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String date = "13-03-2014 10:20:56";

		byte[] password = "hallo123".getBytes(Charset.forName("UTF-8"));

		User u = new User();
		u.setId(1);
		u.setName("Maximilian");
		u.setEmail("max123@hotmail.com");
		u.setPassword(password);

		List<User> user = new ArrayList<User>();
		user.add(u);

		VoteDate vd = new VoteDate();
		vd.setId(1);

		try {
			vd.setDate(sdf.parse(date));
		} catch (ParseException e) {
			System.out
					.println("Beim Parsen des Datums ist ein Fehler aufgetreten!");
		}

		this.session.save(vd);
		this.transaction.commit();

		Session s = sessionFactory.openSession();

		org.hibernate.Query namedQuery = s.getNamedQuery("getAllVoteDates");

		List<VoteDate> list = namedQuery.list();

		assertEquals(vd.getId(), list.get(0).getId());
		assertEquals(vd.getDate(), list.get(0).getDate());
	}

	@Test
	public void testNotification() {
		Notification n = new Notification();
		n.setId(1);
		n.setRead(true);
		n.setText("Dies ist eine Notifizierung");

		this.session.save(n);
		this.transaction.commit();

		Session s = sessionFactory.openSession();

		org.hibernate.Query namedQuery = s.getNamedQuery("getAllNotifications");

		List<Notification> list = namedQuery.list();

		assertEquals(n.getId(), list.get(0).getId());
		assertEquals(n.isRead(), list.get(0).isRead());
		assertEquals(n.getText(), list.get(0).getText());
	}
}