package sigmascheduler.engine.data;

import java.io.Serializable;

import javax.persistence.*;

/**
 * A tiny little reminder that something happened somewhere
 * @author Andreas Vogt, Dominik Kodras, Dominik Scholz, Osman Oezsoy
 * @version 0.1
 */
@Entity
public class Notification implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private User user;

    private String text;

    private boolean isRead;

    public int getId() { return id; }
    public User getUser() { return user; }
    public String getText() { return text; }
    public boolean isRead() { return isRead; }

    public void setId(int id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setText(String text) { this.text = text; }
    public void setRead(boolean isRead) { this.isRead = isRead;}
}
