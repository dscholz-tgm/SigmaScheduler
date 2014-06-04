package sigmascheduler.userinterface.listener;

import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import sigmascheduler.engine.SigmaSchedulerException;
import sigmascheduler.engine.SigmaSchedulerSession;
import sigmascheduler.engine.UserManager;
import sigmascheduler.userinterface.SigmaSchedulerUI;
import sigmascheduler.userinterface.views.LoginView;
import sigmascheduler.userinterface.views.RegisterView;

/**
 * Listenes on the LoginButton
 * @author Dominik Scholz
 * @version 0.1
 */
public class LoginListener implements Button.ClickListener {
    
    private final LoginView loginView;
    
    public LoginListener(LoginView loginView) {
        this.loginView = loginView;
    }
    
    @Override
    public void buttonClick(Button.ClickEvent event) {
        
        String username = loginView.getUsername();
        byte[] password = loginView.getPassword();
        
        try {
            UserManager um = ((SigmaSchedulerUI)UI.getCurrent()).getSigmaSchedulerSession().getUserManager();
            um.loginUser(username, password);
            event.getButton().removeClickShortcut();
            ((SigmaSchedulerUI) loginView.getUI()).displayMain();
        } catch (SigmaSchedulerException ex) {
            loginView.displayErrorMessage(ex.getMessage());
            if(ex.getArgument().equals("register")) loginView.getUI().setContent(new RegisterView());
        }
    }
}
