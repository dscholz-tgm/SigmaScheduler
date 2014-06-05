package sigmascheduler.engine.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Wrapper class for the difficult relationship of event and date
 * @author Andreas Vogt, Dominik Kodras, Dominik Scholz, Osman Oezsoy
 * @version 0.1
 */
@Entity
public class VoteDate implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(cascade = CascadeType.ALL)
    private List<User> voter;

    public int getId() { return id; }
    public Date getDate() { return date; }
    public List<User> getVoter() { return voter; }
    
    public void setId(int id) { this.id = id; }
    public void setDate(Date date) { this.date = date; }
    public void setVoter(List<User> voter) { this.voter = voter; }
}
