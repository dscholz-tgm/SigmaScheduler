package sigmascheduler.engine.data;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import org.hibernate.annotations.*;

@Entity
public class Comment {
	
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
