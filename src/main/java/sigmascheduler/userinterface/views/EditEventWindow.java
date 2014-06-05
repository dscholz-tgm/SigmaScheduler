package sigmascheduler.userinterface.views;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import sigmascheduler.engine.data.VoteDate;
import sigmascheduler.userinterface.listener.CreateEventListener;

/**
 *
 * @author Dominik
 */
public class EditEventWindow extends Window {
    
    private final FormLayout form;
    private TextField name;
    private TextArea description;
    private CheckBox allowMultipleVotes;
    private List<DateField> dateFields;
    private GridLayout dateFieldLayout;
    private Button addDate;
    private static final int MAX_DATES = 15;
    private static final int DEFAULT_DATEFIELD_AMOUNT = 3;
    private Label error;
    private boolean prefill;
    private sigmascheduler.engine.data.Event tempEvent;

    public EditEventWindow() {
        super("New Event");
        
        form = new FormLayout();
        dateFields = new ArrayList<DateField>();
        
        setModal(true);
        setClosable(false);
        setResizable(false);
//        addStyleName("edit-dashboard");
        addStyleName("new-event");
        
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(buildForm());
        layout.addComponent(buildMenu());
        
        setContent(layout);
    }
    
    public EditEventWindow(sigmascheduler.engine.data.Event event) {
        super("Edit Event - " + event.getName());
        tempEvent = event;
        
        form = new FormLayout();
        dateFields = new ArrayList<DateField>();
        
        setModal(true);
        setClosable(false);
        setResizable(false);
//        addStyleName("edit-dashboard");
        addStyleName("new-event");
        
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(buildForm(event));
        layout.addComponent(buildMenu());
        
        setContent(layout);
    }
    
    private Component buildForm() {
        return buildForm(null);
    }
    
    private Component buildForm(sigmascheduler.engine.data.Event event) {
        prefill = event != null;
        
        form.setSizeUndefined();
        form.setMargin(true);
        form.setSpacing(true);
        
        name = new TextField("Event Name");
        name.focus();
        name.selectAll();
        name.setStyleName("event-input");
        if(prefill) name.setValue(event.getName());
        form.addComponent(name);
        
        description = new TextArea("Description");
        description.setStyleName("event-input");
        if(prefill) description.setValue(event.getDescription());
        form.addComponent(description);
        
        allowMultipleVotes = new CheckBox("Allow Multiple Votes", true);
        if(prefill) allowMultipleVotes.setValue(event.isAllowMultipleVotes());
        form.addComponent(allowMultipleVotes);
        
        dateFieldLayout = new GridLayout(3,5);
        dateFieldLayout.setSpacing(true);
        dateFieldLayout.setCaption("Dates");
        int count = 0;
        if(prefill) {
            for(VoteDate voteDate : event.getVoteDates()) {
                count++;
                DateField dateField = buildDateField();
                dateField.setValue(voteDate.getDate());
                dateFieldLayout.addComponent(new DateComponent(dateField));
            }
        }
        while(dateFieldLayout.getComponentCount() < DEFAULT_DATEFIELD_AMOUNT) dateFieldLayout.addComponent(new DateComponent(buildDateField()));
        form.addComponent(dateFieldLayout);
        
        addDate = new Button("+");
        addDate.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                dateFieldLayout.replaceComponent(addDate, new DateComponent(buildDateField()));
                if(dateFieldLayout.getComponentCount() < MAX_DATES) dateFieldLayout.addComponent(addDate);
            }
        });
        addDate.addStyleName("add-button");
        dateFieldLayout.addComponent(addDate);
        return form;
    }

    private Component buildMenu() {
        HorizontalLayout menu = new HorizontalLayout();
        menu.setMargin(true);
        menu.setSpacing(true);
        menu.addStyleName("footer");
        menu.setWidth("100%");
        
        Button ok = new Button("Save");
        ok.addStyleName("wide");
        ok.addStyleName("default");
        ok.addClickListener(new CreateEventListener(this));
        ok.setClickShortcut(KeyCode.ENTER, null);
        menu.addComponent(ok);
        menu.setExpandRatio(ok, 1);
        menu.setComponentAlignment(ok, Alignment.TOP_RIGHT);

        Button cancel = new Button("Cancel");
        cancel.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                //Are you suer you want to delete blablabla...
                close();
            }
        });
        cancel.setClickShortcut(KeyCode.ESCAPE, null);
        menu.addComponent(cancel);
        
        return menu;
    }

    private DateField buildDateField() {
        DateField dateField = new DateField();
        dateField.setDateFormat("dd.MM.yyyy");
        dateField.setLocale(Locale.UK);
        dateField.setStyleName("reindeer");
//        dateField.setResolution(Resolution.MINUTE);
        dateField.setWidth("100px");
        dateFields.add(dateField);
        return dateField;
    }

    private class DateComponent extends HorizontalLayout {
        public DateComponent(final DateField dateField) {
            setSpacing(true);
            setStyleName("datefield-component");
            addComponent(dateField);
            Button deleteField = new Button("×");
            deleteField.setPrimaryStyleName("delete-datefield-button");
            deleteField.addClickListener(new ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    dateFields.remove(dateField);
                    refillDateFieldLayout();
                }
            });
            addComponent(deleteField);
        }
    }
    
    private void refillDateFieldLayout() {
        dateFieldLayout.removeAllComponents();
        for(DateField dateField : dateFields) dateFieldLayout.addComponent(new DateComponent(dateField));
        if(dateFieldLayout.getComponentCount() < MAX_DATES) dateFieldLayout.addComponent(addDate);
    }
    
    public void displayErrorMessage(String message) {
        //Remove old error message
        if(error != null) form.removeComponent(error);

        //Add new error message
        error = new Label(message,ContentMode.HTML);
        error.addStyleName("error");
        error.setSizeUndefined();
        error.addStyleName("light");

        //Add animation
        error.addStyleName("v-animate-reveal");
        form.addComponent(error);
    }
    
    public String getEventName() { return name.getValue(); }
    public String getEventDescription() { return description.getValue(); }
    public boolean getAllowMultipleVotes() { return allowMultipleVotes.getValue(); }
    public List<DateField> getDateFields() {  return dateFields; }
    public boolean getPrefill() { return prefill; }
    public sigmascheduler.engine.data.Event getEvent() { return tempEvent; }
}
    