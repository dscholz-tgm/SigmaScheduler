package sigmascheduler.engine.data;

import javax.persistence.*;

/**
 * Mappen der Klasse Notification mittels Hibernate-Annotations
 * 
 * @author Kodras, Oezsoy, Vogt
 */
@Entity
@Table(name = "notification")
public class Notification {

	@Id
	@GeneratedValue
	private int id;

	@OneToOne
	private User user;

	private String text;

	private boolean isRead;

	// Getter-Methoden

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public String getText() {
		return text;
	}

	public boolean isRead() {
		return isRead;
	}

	// Setter-Methoden

	public void setId(int id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
}