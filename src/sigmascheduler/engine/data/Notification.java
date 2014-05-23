package sigmascheduler.engine.data;

import javax.persistence.Column;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Entity;

@Entity
public class Notification {
	
	@ManyToMany
	private User user;
	
	@Column
	private String text;
	
	@Column
	private boolean read;
	
	public boolean isRead() {
		return read;
	}

	public User getUser() {
		return user;
	}

	public String getText() {
		return text;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setRead(boolean read) {
		this.read = read;
	}
	
}
