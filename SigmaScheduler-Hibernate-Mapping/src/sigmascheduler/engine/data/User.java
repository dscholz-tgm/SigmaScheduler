package sigmascheduler.engine.data;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

/**
 * Mappen der Klasse User mittels Hibernate-Annotations
 * 
 * @author Kodras, Oezsoy, Vogt
 */
@Entity
@Table(name = "benutzer")
public class User implements Serializable {
	
	@Id
	@GeneratedValue
	private int id;

	@Size(min = 2, max = 150)
	@Pattern(regexp = "[a-zA-Z0-9]")
	private String name;

	@Email
	private String email;

	@Pattern(regexp = "[a-zA-Z0-9]")
	private byte[] password;

	@OneToOne
	private Notification notification;

	// Getter-Methoden

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public byte[] getPassword() {
		return password;
	}

	public Notification getNotification() {
		return notification;
	}

	public int getEid() {
		return id;
	}

	// Setter-Methoden

	public void setId(int id) {
		this.id = id;
	}

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

	public void setNotification(Notification notification) {
		this.notification = notification;
	}
}