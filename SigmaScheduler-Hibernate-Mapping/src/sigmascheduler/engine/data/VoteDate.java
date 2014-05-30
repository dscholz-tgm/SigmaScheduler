package sigmascheduler.engine.data;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class VoteDate {
	@Id
	@GeneratedValue
	private int id;
	
	private Date date;
	
	@OneToMany
	private List<User> benutzer;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<User> getUser() {
		return benutzer;
	}

	public void setUser(List<User> benutzer) {
		this.benutzer = benutzer;
	}
}
