package sigmascheduler.userinterface.listener;

import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import sigmascheduler.engine.EventManager;
import sigmascheduler.engine.SigmaSchedulerException;
import sigmascheduler.userinterface.SigmaSchedulerUI;
import sigmascheduler.userinterface.views.EditEventWindow;

/**
 * Listenes on the publish of an event
 * @author Dominik Scholz
 * @version 0.1
 */
public class PublishEventListener implements Button.ClickListener {
    
    private EditEventWindow window;
    
    public PublishEventListener(EditEventWindow window) {
        this.window = window;
    }
    
    @Override
    public void buttonClick(Button.ClickEvent event) {
        try {
            EventManager em = ((SigmaSchedulerUI)UI.getCurrent()).getSigmaSchedulerSession().getEventManager();
            em.publishEvent(window.getEvent());
            window.close();
            Notification note = new Notification("Published Event \"" + window.getEvent().getName() + "\"", Notification.Type.TRAY_NOTIFICATION);
            note.show(UI.getCurrent().getPage());
        } catch (SigmaSchedulerException ex) {
            window.displayErrorMessage(ex.getMessage());
        }
    }
}
