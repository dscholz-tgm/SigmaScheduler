package sigmascheduler.engine.data;

import java.util.*;

import javax.persistence.*;

/**
 * Mappen der Klasse VoteDate mittels Hibernate-Annotations
 * 
 * @author Kodras, Oezsoy, Vogt
 */
@Entity
public class VoteDate {

	@Id
	@GeneratedValue
	private int id;
	
	private Date date;

	@OneToMany
	private List<User> benutzer;

	// Getter-Methoden

	public Date getDate() {
		return date;
	}

	public int getId() {
		return id;
	}

	public List<User> getBenutzer() {
		return benutzer;
	}

	// Setter-Methoden

	public void setDate(Date date) {
		this.date = date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBenutzer(List<User> benutzer) {
		this.benutzer = benutzer;
	}
}