package sigmascheduler.engine;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Image;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import sigmascheduler.engine.data.User;

/**
 * Manages the interaction with the User class
 * @author Dominik Scholz
 * @version 0.2
 */
public class UserManager {
    
    private SigmaSchedulerSession session;
    private DataManager manager;
    
    public UserManager(SigmaSchedulerSession session) {
        this.session = session;
        manager = session.getDataManager();
    }
    
    public User registerUser(String name, String email, byte[] password) throws SigmaSchedulerException {
        //Check if user is alreay in the database
        if(!manager.executeQuery("getUser",name).isEmpty()) throw new SigmaSchedulerException("Username already registerd");
        
        //Creating new user
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(hash(password));
        
        //Saving new user
        DataManager.get().save(user);
        
        return user;
    }

    public User loginUser(String name, byte[] password) throws SigmaSchedulerException {
        if(name.equals("")) throw new SigmaSchedulerException("Empty Name!");
        if(password.length == 0) throw new SigmaSchedulerException("Empty Password!");
        
        List<User> result = manager.executeQuery("getUser",name);
        
        //Check if user is registerd
        if(result.isEmpty()) throw new SigmaSchedulerException("register","Username not registerd");
        
        User user = (User) result.get(0);
        
        //Check if the password is right
        if(!Arrays.equals(user.getPassword(), hash(password))) throw new SigmaSchedulerException("forgotpassword","Wrong password");
        
        //Setting the user for the current session
        session.setUser(user);
        return user;
    }
    
    public List<User> getAllUsers() throws SigmaSchedulerException {
        return manager.executeQuery("getAllUsers");
    }

    public List<User> searchUser(String name) {
//        hql query to search all users with regular expression "name"
        return null;
    }
    
    private byte[] hash (byte[] password) throws SigmaSchedulerException {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ex) {
            throw new SigmaSchedulerException("Hash Problem, should never ever happen :/");
        }
        //Salt hash maybe?
        return messageDigest.digest(password);
    }
    
            
    public Image getAvatar() {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
        }
        byte[] hashAr = messageDigest.digest(session.getUser().getEmail().getBytes(Charset.forName("UTF-8")));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashAr) sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));
        return new Image(null, new ExternalResource ("http://www.gravatar.com/avatar/"+sb.toString()+".jpg?d=identicon&s=240"));
    }
    
}
