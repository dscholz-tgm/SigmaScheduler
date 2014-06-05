package sigmascheduler.userinterface.listener;

import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sigmascheduler.engine.EventManager;
import sigmascheduler.engine.SigmaSchedulerException;
import sigmascheduler.userinterface.SigmaSchedulerUI;
import sigmascheduler.userinterface.views.EditEventWindow;

/**
 * Listenes on the create of an event
 * @author Dominik Scholz
 * @version 0.1
 */
public class CreateEventListener implements Button.ClickListener {
    
    private EditEventWindow window;
    
    public CreateEventListener(EditEventWindow window) {
        this.window = window;
    }
    
    @Override
    public void buttonClick(Button.ClickEvent event) {
        
        String name = window.getEventName();
        if(name == null || name.isEmpty() || name.trim().isEmpty()) {
            window.displayErrorMessage("Eventname cannot be empty!");
            return;
        }
        
        String description = window.getEventDescription();
        boolean allowMultipleVotes = window.getAllowMultipleVotes();
        
        List<DateField> dateFields = window.getDateFields();
        List<Date> dates = new ArrayList<Date>();
        Date date;
        for(DateField dateField : dateFields) {
            date = dateField.getValue();
            if(date != null) dates.add(dateField.getValue());
        }
        
        if(dates.isEmpty()) {
            window.displayErrorMessage("You have to specify at least one date!");
            return;
        }
        
        try {
            EventManager em = ((SigmaSchedulerUI)UI.getCurrent()).getSigmaSchedulerSession().getEventManager();
            if(window.getPrefill()) em.createEvent(name, description, allowMultipleVotes, dates, window.getEvent());
            else em.createEvent(name,description,allowMultipleVotes,dates);
            event.getButton().removeClickShortcut();
            window.close();
            Notification note;
            note = window.getPrefill() ? 
                new Notification("Edited Event \"" + name + "\"", Notification.Type.TRAY_NOTIFICATION) :
                new Notification("Created new Event \"" + name + "\"", Notification.Type.TRAY_NOTIFICATION);
            note.show(UI.getCurrent().getPage());
        } catch (SigmaSchedulerException ex) {
            window.displayErrorMessage(ex.getMessage());
        }
    }
}
