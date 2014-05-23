package sigmascheduler.engine.data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Entity;
import org.hibernate.validator.constraints.Email;
/**
 * Event User
 * 
 * @author Vogt , Kodras
 * 
 */
@Entity
public class User {
	@Id
	private int id;
	
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
	
	//@ManyToMany
	//private Event event;

	@OneToOne
	private Notification notification;
	//Getter
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public byte[] getPassword() {
		return password;
	}

	//public Event getEvent() {
		//return event;
	//}

	public Notification getNotification() {
		return notification;
	}
	
	public int getEid() {
		return id;
	}
	
	//Setter
	
	public void setEid(int id) {
		this.id = id;
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

	//public void setEvent(Event event) {
		//this.event = event;
	//}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

}
