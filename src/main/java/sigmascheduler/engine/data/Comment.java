package sigmascheduler.engine.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Comment from a user in an event discussion
 * (may contain rage or vile language!)
 * @author Andreas Vogt, Dominik Kodras, Dominik Scholz, Osman Oezsoy
 * @version 0.1
 */
@Entity
public class Comment implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    
    private User author;
    
    @Temporal(TemporalType.DATE)
    private Date createDate;
    
    private String text;
    
    @ManyToOne
    private Event event;

    public int getId() { return id; }
    public User getAuthor() { return author; }
    public Date getCreateDate() { return createDate; }
    public String getText() { return text; }
    public Event getEvent() {  return event; }

    public void setId(int id) { this.id = id; }
    public void setAuthor(User author) { this.author = author; }
    public void setCreateDate(Date createDate) { this.createDate = createDate; }
    public void setText(String text) { this.text = text; }
    public void setEvent(Event event) { this.event = event; }
}
