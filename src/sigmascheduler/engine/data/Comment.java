package sigmascheduler.engine.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.*;
/**
 * Event Comment
 * 
 * @author Vogt , Kodras
 * 
 */
@Entity
public class Comment {
	@Id
	private int id;
	
	@Column
	private User author;
	
	@Column
	private Date createDate;
	
	@Column
	private String text;
	
	@OneToMany
	private Event event;

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
	
	public int getEid() {
		return id;
	}
	
	//Setter
	
	public void setEid(int id) {
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
