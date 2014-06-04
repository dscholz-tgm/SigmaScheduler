package sigmascheduler.engine.data;

import java.io.Serializable;

import javax.persistence.*;

/**
 * A user is a hopefully human identity who found the way all through his life
 * to the point where he found our tool... 
 * we played an important role in that fraction of a second of his life
 * @author Andreas Vogt, Dominik Kodras, Dominik Scholz, Osman Oezsoy
 * @version 0.1
 */
@Entity
@NamedQuery(
    //Returns the user with the associated name
    name="getUser",
    query="from User u where u.name = :id")
@Table(name="ssuser")
public class User implements Serializable {
    
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String email;

    private byte[] password;

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public byte[] getPassword() { return password; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(byte[] password) { this.password = password; }
}
