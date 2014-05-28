package sigmascheduler.engine.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;





import org.hibernate.annotations.Entity;
/**
 * Notification Mapping
 * 
 * @author Vogt,Kodras
 * 
 */
@Entity
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	private User user;
	
	@Column
	private String text;
	
	@Column
	private boolean read;
	
	//getter
	public boolean getRead() {
		return read;
	}

	public User getUser() {
		return user;
	}

	public String getText() {
		return text;
	}
	
	public int getEid() {
		return id;
	}
	
	//Setter
	
	public void setEid(int id) {
		this.id = id;
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
