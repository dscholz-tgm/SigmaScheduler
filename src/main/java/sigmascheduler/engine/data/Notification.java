package sigmascheduler.engine.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * A tiny little reminder that something happened somewhere
 * @author Andreas Vogt, Dominik Kodras, Dominik Scholz, Osman Oezsoy
 * @version 0.2
 */
@Entity
@NamedQueries({
    @NamedQuery(
        //Returns all unread notifications of a user
        name="getUnreadNotifications",
        query="from Notification n where u.name = :id and u.isRead = false"),
    @NamedQuery(
        //Returns all notifications of a user
        name="getNotifications",
        query="from Notification n where u.name = :id ")
})
public class Notification implements Serializable, Comparable {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private User user;

    private String text;

    private boolean isRead;
    
    @Temporal(TemporalType.DATE)
    private Date date;

    public int getId() { return id; }
    public User getUser() { return user; }
    public String getText() { return text; }
    public boolean isRead() { return isRead; }
    public Date getDate() { return date; }

    public void setId(int id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setText(String text) { this.text = text; }
    public void setRead(boolean isRead) { this.isRead = isRead;}
    public void setDate(Date date) { this.date = date; }

    @Override
    public int compareTo(Object o) {
        if(o.getClass() != this.getClass()) return 0;
        else return date.compareTo(((Notification) o).getDate());
    }
}
