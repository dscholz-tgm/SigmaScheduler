package sigmascheduler.engine;

import sigmascheduler.engine.data.User;

/**
 * A Session Representaion
 * @author Dominik Scholz
 * @version 0.2
 */
public class SigmaSchedulerSession {
    
    private final DataManager dataManager;
    private final UserManager userManager;
    private final EventManager eventManager;
    private User user;
    
    public SigmaSchedulerSession() {
        dataManager = DataManager.get();
        userManager = new UserManager(this);
        eventManager = new EventManager(this);
    }
    
    public DataManager getDataManager() { return dataManager; }
    public UserManager getUserManager() { return userManager; }
    public EventManager getEventManager() { return eventManager; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
