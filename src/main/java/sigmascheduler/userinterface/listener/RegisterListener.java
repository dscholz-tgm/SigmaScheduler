package sigmascheduler.userinterface.listener;

import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import java.util.Arrays;
import sigmascheduler.engine.SigmaSchedulerException;
import sigmascheduler.engine.UserManager;
import sigmascheduler.userinterface.SigmaSchedulerUI;
import sigmascheduler.userinterface.views.RegisterView;

/**
 * Listenes on the registration
 * @author Dominik Scholz
 * @version 0.1
 */
public class RegisterListener implements Button.ClickListener {
    
    private RegisterView registerView;
    
    public RegisterListener(RegisterView registerView) {
        this.registerView = registerView;
    }
    
    @Override
    public void buttonClick(Button.ClickEvent event) {
        
        String username = registerView.getUsername();
        byte[] password = registerView.getPassword();
        byte[] passwordVerification = registerView.getPasswordVerification();
        String email = registerView.getEmail();
        
        if(!Arrays.equals(password, passwordVerification)) {
            registerView.displayErrorMessage("The two passwordfields doesn't match!");
            return;
        }
        
        try {
            UserManager um = ((SigmaSchedulerUI)UI.getCurrent()).getSigmaSchedulerSession().getUserManager();
            um.registerUser(username,email,password);
            event.getButton().removeClickShortcut();
            ((SigmaSchedulerUI) registerView.getUI()).displayLogin();
        } catch (SigmaSchedulerException ex) {
            registerView.displayErrorMessage(ex.getMessage());
        }
    }
}
