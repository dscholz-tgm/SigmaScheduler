package sigmascheduler.userinterface.views;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseListener;
import java.util.List;
import sigmascheduler.engine.SigmaSchedulerException;
import sigmascheduler.engine.SigmaSchedulerSession;
import sigmascheduler.userinterface.SigmaSchedulerUI;

/**
 *
 * @author Dominik
 */
public class MyEventsView extends HorizontalLayout implements View {

    private TabSheet eventSheet;
    private HorizontalLayout events;
    private SigmaSchedulerSession session = ((SigmaSchedulerUI) UI.getCurrent()).getSigmaSchedulerSession();

    @Override
    public void enter(ViewChangeEvent event) {
        setSizeFull();
        addStyleName("reports");

        addComponent(buildEventsView());
    }

    private Component buildEventsView() {
        eventSheet = new TabSheet();
        eventSheet.setSizeFull();
        eventSheet.addStyleName("borderless");
        eventSheet.addStyleName("editors");

        final VerticalLayout center = new VerticalLayout();
        center.setSizeFull();
        center.setCaption("Created Events");
        eventSheet.addComponent(center);

        VerticalLayout titleAndDrafts = new VerticalLayout();
        titleAndDrafts.setSizeUndefined();
        titleAndDrafts.setSpacing(true);
        titleAndDrafts.addStyleName("drafts");
        center.addComponent(titleAndDrafts);
        center.setComponentAlignment(titleAndDrafts, Alignment.MIDDLE_CENTER);

        Label draftsTitle = new Label("My Events");
        draftsTitle.addStyleName("h1");
        draftsTitle.setSizeUndefined();
        titleAndDrafts.addComponent(draftsTitle);
        titleAndDrafts.setComponentAlignment(draftsTitle, Alignment.TOP_CENTER);

        events = new HorizontalLayout();
        events.setSpacing(true);
        titleAndDrafts.addComponent(events);
        
        fillEvents();

        return eventSheet;
    }

    private void fillEvents() {
        events.removeAllComponents();
        List<sigmascheduler.engine.data.Event> managedEvents;
        try {
            managedEvents = session.getEventManager().getManagedEvents(session.getUser());
        } catch (SigmaSchedulerException ex) {
            return;
        }
        for(final sigmascheduler.engine.data.Event event : managedEvents) {
            CssLayout eventBox = new CssLayout();
            eventBox.addStyleName("event-box");
            eventBox.addStyleName("state-" + event.getState().toString().toLowerCase());
//            Image draft = new Image(null, new ThemeResource(
//                    "img/draft-report-thumb.png"));
//            eventBox.addComponent(draft);
            Label draftTitle = new Label(
                    event.getName() + "<br />"
                            + "<span>Created on " + event.getCreateDate()+ "</span><br />"
                            + "<small>Status: " + event.getState().toString() + "</small><br />",
                    ContentMode.HTML);
            draftTitle.setSizeUndefined();
            eventBox.addComponent(draftTitle);
            events.addComponent(eventBox);
            // TODO layout bug, we need to set the alignment also for the first
            // child
            events.setComponentAlignment(eventBox, Alignment.MIDDLE_CENTER);

            final Button delete = new Button("×");
            delete.setPrimaryStyleName("delete-button");
            eventBox.addComponent(delete);
            delete.addClickListener(new ClickListener() {
                @Override
                public void buttonClick(ClickEvent clickEvent) {
                    try {
                        session.getEventManager().delete(event);
                    } catch (SigmaSchedulerException ex) {
                    }
                }
            });

            eventBox.addLayoutClickListener(new LayoutClickListener() {
                @Override
                public void layoutClick(LayoutClickEvent clickEvent) {
                    if (clickEvent.getButton() == MouseButton.LEFT && clickEvent.getChildComponent() != delete) {
                        if(UI.getCurrent().getWindows().isEmpty()) {
                            Window w = new EditEventWindow(event);
                            UI.getCurrent().addWindow(w);
                            w.addCloseListener(new CloseListener() {
                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    fillEvents();
                                }
                            });
                        }
                    }
                }
            });
//            draft.setDescription("Click to edit");
            delete.setDescription("Delete Event");
            events.addComponent(eventBox);
        }

        VerticalLayout createBox = new VerticalLayout();
        createBox.setWidth(null);
        createBox.addStyleName("create");
        Button create = new Button("Create New");
        create.addStyleName("default");
        createBox.addComponent(create);
        createBox.setComponentAlignment(create, Alignment.MIDDLE_CENTER);
        events.addComponent(createBox);
        create.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                if(UI.getCurrent().getWindows().isEmpty()) {
                    Window w = new EditEventWindow();
                    UI.getCurrent().addWindow(w);
                    w.addCloseListener(new CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            fillEvents();
                        }
                    });
                }
            }
        });
    }
}
    