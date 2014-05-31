package sigmascheduler.engine.data;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

/**
 * Mappen der Klasse Comment mittels Hibernate-Annotations
 * 
 * @author Kodras, Oezsoy, Vogt
 */
@NamedQuery(name = "getAllComments", query = "FROM Comment")
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

	@Id
	@GeneratedValue
	private int id;

	private User author;

	private Date createDate;

	private String text;

	@ManyToOne
	private Event event;

	// Getter-Methoden

	public User getAuthor() {
		return author;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public String getText() {
		return text;
	}

	public Event getEvent() {
		return event;
	}

	public int getId() {
		return id;
	}

	// Setter-Methoden

	public void setId(int id) {
		this.id = id;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}