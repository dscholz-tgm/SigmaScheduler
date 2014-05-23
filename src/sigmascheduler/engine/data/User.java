package sigmascheduler.engine.data;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Entity;
import org.hibernate.validator.constraints.Email;

@Entity
public class User {
	
	@Column
	@Size(min = 2, max = 150)
    @Pattern(regexp = "[a-zA-Z0-9]")
	private String name;
	
	@Column
	@Email
	private String email;
	
	@Column
	@Pattern(regexp = "[a-zA-Z0-9]")
	private byte[] password;
	
	@ManyToMany
	private Event event;

	@ManyToMany
	private Notification notification;

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public byte[] getPassword() {
		return password;
	}

	public Event getEvent() {
		return event;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

}
