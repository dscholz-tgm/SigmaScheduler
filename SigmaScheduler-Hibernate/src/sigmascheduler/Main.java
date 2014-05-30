package sigmascheduler;

import java.text.*;

import javax.persistence.*;

import org.apache.log4j.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

public class Main {
	private static final Logger log = Logger.getLogger(Main.class);

	static SimpleDateFormat dateForm = new SimpleDateFormat("dd.MM.yyyy");
	static SimpleDateFormat timeForm = new SimpleDateFormat("dd.MM.yyyy mm:hh");

	private static final SessionFactory sessionFactory;

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

	/**
	 * Eine Methode, die die aktuelle SessionFactory zurueckliefert.
	 * 
	 * @return sessionFactory Die aktuelle SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Ein Private-Konstruktor.
	 */
	private Main() {
		super();
	}

	/**
	 * Eine Hauptmethode, die die einzelnen Tasks ausfuehrt. Bei Programmstart
	 * wird diese Methode aufgerufen.
	 * 
	 * @param args
	 *            Start-Argumente
	 */
	public static void main(String[] args) {
		log.setLevel(Level.ALL); // Setzen des Log-Levels

		try {
			log.info("\"Hibernate-Mapping wurde abgeschlossen\"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Eine Methode, die fuer das Fuellen der Datenbank dient. Diese Methode
	 * wird aber nicht verwendet in diesem Beispiel.
	 * 
	 * @param em
	 *            EntityManager
	 * @throws ParseException
	 */
	public static void fillDB(EntityManager em) throws ParseException {
		// dateForm.parse("01.01.1930")
	}
}