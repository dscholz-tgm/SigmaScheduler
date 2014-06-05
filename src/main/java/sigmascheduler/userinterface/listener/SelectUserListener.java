package sigmascheduler.userinterface.listener;

import com.vaadin.ui.Button;
import sigmascheduler.userinterface.views.EditEventWindow;
import sigmascheduler.userinterface.views.SelectUserWindow;

/**
 * Listenes on the LoginButton
 * @author Dominik Scholz
 * @version 0.1
 */
public class SelectUserListener implements Button.ClickListener {
    
    private SelectUserWindow selectUserWindow;
    private EditEventWindow editEventWindow;
    
    public SelectUserListener(EditEventWindow editEventWindow) {
        this.editEventWindow = editEventWindow;
    }
    
    @Override
    public void buttonClick(Button.ClickEvent event) {
        editEventWindow.addMember(selectUserWindow.getSelectedUser());
    }

    public void setSelectUserWindow(SelectUserWindow selectUserWindow) {
        this.selectUserWindow = selectUserWindow;
    }
}
