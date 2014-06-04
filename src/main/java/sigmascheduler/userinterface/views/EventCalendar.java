package sigmascheduler.userinterface.views;

import com.vaadin.addon.calendar.event.BasicEvent;
import com.vaadin.addon.calendar.event.CalendarEvent;
import com.vaadin.addon.calendar.event.CalendarEventProvider;
import com.vaadin.addon.calendar.gwt.client.ui.VCalendar;
import com.vaadin.addon.calendar.ui.Calendar;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.BackwardEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.DateClickEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClick;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClickHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.ForwardEvent;
import com.vaadin.addon.calendar.ui.handler.BasicBackwardHandler;
import com.vaadin.addon.calendar.ui.handler.BasicDateClickHandler;
import com.vaadin.addon.calendar.ui.handler.BasicEventMoveHandler;
import com.vaadin.addon.calendar.ui.handler.BasicEventResizeHandler;
import com.vaadin.addon.calendar.ui.handler.BasicForwardHandler;
import com.vaadin.addon.calendar.ui.handler.BasicWeekClickHandler;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import sigmascheduler.engine.SigmaSchedulerException;
import sigmascheduler.engine.SigmaSchedulerSession;
import sigmascheduler.engine.data.VoteDate;
import sigmascheduler.userinterface.SigmaSchedulerUI;

/**
 *
 * @author Dominik
 */
public class EventCalendar extends VerticalLayout implements View {

    private EventProvider provider = new EventProvider();
    private final Calendar cal;
    private final GregorianCalendar calendar = new GregorianCalendar();
    private Date currentMonthsFirstDate = null;

    public EventCalendar() {
        Label header = new Label("Event Calendar");
        header.addStyleName("h1");
        addComponent(header);
        addStyleName("timeline");
        
        cal = new Calendar(provider);
        cal.setSizeFull();
        cal.setLocale(Locale.UK);

        cal.setStartDate(new Date());
        cal.setEndDate(new Date());
        currentMonthsFirstDate = new GregorianCalendar(calendar.get(GregorianCalendar.YEAR), calendar.get(GregorianCalendar.MONTH), 1).getTime();

        // Make sure the date is in the same year as today
        cal.setHandler(new BasicBackwardHandler() {
            @Override
            protected void setDates(BackwardEvent event, Date start, Date end) {
                java.util.Calendar calendar = event.getComponent()
                        .getInternalCalendar();
                if (isThisYear(calendar, end) && isThisYear(calendar, start)) {
                    super.setDates(event, start, end);
                }
            }
        });

        // Make sure the date is in the same year as today
        cal.setHandler(new BasicForwardHandler() {
            @Override
            protected void setDates(ForwardEvent event, Date start, Date end) {
                java.util.Calendar calendar = event.getComponent()
                        .getInternalCalendar();
                if (isThisYear(calendar, start) && isThisYear(calendar, end)) {
                    super.setDates(event, start, end);
                }
            }
        });

        // Set a date click handler
        cal.setHandler(new BasicDateClickHandler() {
            @Override
            public void dateClick(DateClickEvent event) {
//                Calendar cal = event.getComponent();
//                long currentCalDateRange = cal.getEndDate().getTime()
//                        - cal.getStartDate().getTime();
//
//                if (currentCalDateRange < VCalendar.DAYINMILLIS) {
//                    // Change the date range to the current week
//                    cal.setStartDate(cal.getFirstDateForWeek(event.getDate()));
//                    cal.setEndDate(cal.getLastDateForWeek(event.getDate()));
//
//                } else {
//                    // Default behaviour, change date range to one day
//                    super.dateClick(event);
//                }
            }
        });

        // allow moving to week view by clicking the weeknumber only if the
        // weeks start and end dates are on the current month
        cal.setHandler(new BasicWeekClickHandler() {
//            @Override
//            protected void setDates(WeekClick event, Date start, Date end) {
//                java.util.Calendar calendar = event.getComponent()
//                        .getInternalCalendar();
//                if (isThisMonth(calendar, start) && isThisMonth(calendar, end)) {
//                    super.setDates(event, start, end);
//                }
//            }
        });

        cal.setHandler(new EventClickHandler() {

            private static final long serialVersionUID = 4548304318112120161L;

            public void eventClick(EventClick event) {
                BasicEvent e = (BasicEvent) event.getCalendarEvent();
            }
        });

        // Set the event move listener
        cal.setHandler(new BasicEventMoveHandler() {
//            private java.util.Calendar javaCalendar;
//
//            @Override
//            public void eventMove(MoveEvent event) {
//                javaCalendar = event.getComponent().getInternalCalendar();
//                super.eventMove(event);
//
//            }
//
//            @Override
//            protected void setDates(CalendarEventEditor event, Date start,
//                    Date end) {
//                if (isThisMonth(javaCalendar, start)
//                        && isThisMonth(javaCalendar, end)) {
//                    super.setDates(event, start, end);
//                }
//            }
        });

        cal.setHandler(new BasicEventResizeHandler() {
//            private static final long twelveHoursInMs = 12 * 60 * 60 * 1000;
//
//            @Override
//            protected void setDates(CalendarEventEditor event, Date start,
//                    Date end) {
//                long eventLength = end.getTime() - start.getTime();
//                if (eventLength <= twelveHoursInMs) {
//                    super.setDates(event, start, end);
//                }
//            }
        });

        Button monthViewButton = new Button("Show month");
        monthViewButton.addListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                monthMode();
            }
        });
        monthMode();
        
        final Label label = new Label();
        
        Button next = new Button("next", new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                calendar.setTime(currentMonthsFirstDate);
                calendar.add(GregorianCalendar.MONTH, 1);
                currentMonthsFirstDate = calendar.getTime();
                cal.setStartDate(currentMonthsFirstDate);
                DateFormatSymbols s = new DateFormatSymbols(getLocale());
                String month = s.getShortMonths()[calendar
                        .get(GregorianCalendar.MONTH)];
                label.setValue(month + " "
                        + calendar.get(GregorianCalendar.YEAR));
                calendar.add(GregorianCalendar.MONTH, 1);
                calendar.add(GregorianCalendar.DATE, -1);
                cal.setEndDate(calendar.getTime());
            }

        });

        Button prev = new Button("prev", new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                calendar.setTime(currentMonthsFirstDate);
                calendar.add(GregorianCalendar.MONTH, -1);
                currentMonthsFirstDate = calendar.getTime();
                cal.setStartDate(currentMonthsFirstDate);
                DateFormatSymbols s = new DateFormatSymbols(getLocale());
                String month = s.getShortMonths()[calendar
                        .get(GregorianCalendar.MONTH)];
                label.setValue(month + " "
                        + calendar.get(GregorianCalendar.YEAR));
                calendar.add(GregorianCalendar.MONTH, 1);
                calendar.add(GregorianCalendar.DATE, -1);
                cal.setEndDate(calendar.getTime());
            }

        });


        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        layout.addComponent(monthViewButton);
        HorizontalLayout hl = new HorizontalLayout();
        hl.addComponent(prev);
        hl.setComponentAlignment(prev, Alignment.BOTTOM_LEFT);
        hl.addComponent(label);
        hl.setComponentAlignment(label, Alignment.BOTTOM_CENTER);
        hl.addComponent(next);
        hl.setComponentAlignment(label, Alignment.BOTTOM_RIGHT);
        hl.setSizeFull();
        hl.setWidth("100%");
        layout.addComponent(hl);
        layout.addComponent(cal);
        layout.setExpandRatio(cal, 1);
        this.addComponent(layout);
        this.setExpandRatio(layout, 1);
        setSizeFull();
    }

    public static boolean isThisYear(java.util.Calendar calendar, Date date) {
        calendar.setTime(new Date());
        int thisYear = calendar.get(java.util.Calendar.YEAR);
        calendar.setTime(date);
        return calendar.get(java.util.Calendar.YEAR) == thisYear;
    }

    public static boolean isThisMonth(java.util.Calendar calendar, Date date) {
        calendar.setTime(new Date());
        int thisMonth = calendar.get(java.util.Calendar.MONTH);
        calendar.setTime(date);
        return calendar.get(java.util.Calendar.MONTH) == thisMonth;
    }
    
    public void monthMode() {
        GregorianCalendar gc = new GregorianCalendar(cal.getTimeZone(),
        cal.getLocale());
        gc.setTime(cal.getStartDate());

        long currentCalDateRange = cal.getEndDate().getTime()
                - cal.getStartDate().getTime();
        if (currentCalDateRange > (VCalendar.DAYINMILLIS * 7)) {
            return;
        }

        // Reset calendar's start time to the target month's first day.
        gc.set(GregorianCalendar.DATE, gc
                .getMinimum(GregorianCalendar.DATE));
        gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
        gc.set(GregorianCalendar.MINUTE, 0);
        gc.set(GregorianCalendar.SECOND, 0);
        gc.set(GregorianCalendar.MILLISECOND, 0);
        cal.setStartDate(gc.getTime());
        gc.add(GregorianCalendar.MONTH, 1);
        gc.add(GregorianCalendar.DATE, -1);
        cal.setEndDate(gc.getTime());

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
    
    public static class EventProvider implements CalendarEventProvider {

        private static final long serialVersionUID = -3655982234130426761L;

        private List<CalendarEvent> events = new ArrayList<CalendarEvent>();

        public EventProvider() {
            events = new ArrayList<CalendarEvent>();
            SigmaSchedulerSession session = ((SigmaSchedulerUI) UI.getCurrent()).getSigmaSchedulerSession();
            List<sigmascheduler.engine.data.Event> managedEvents;
            try {
                managedEvents = session.getEventManager().getManagedEvents(session.getUser());
                BasicEvent calenderEvent;
                for(sigmascheduler.engine.data.Event event : managedEvents) {
                    for(VoteDate date : event.getVoteDates()) {
                        calenderEvent = new BasicEvent();
                        calenderEvent.setCaption(event.getName());
                        calenderEvent.setDescription(event.getDescription());
                        calenderEvent.setStart(date.getDate());
                        calenderEvent.setEnd(date.getDate());
                        calenderEvent.setAllDay(true);
                        events.add(calenderEvent);
                    }
                }
            } catch (SigmaSchedulerException ex) {
            }
        }

        public void addEvent(CalendarEvent BasicEvent) {
            events.add(BasicEvent);
        }

        @Override
        public List<CalendarEvent> getEvents(Date startDate, Date endDate) {
            return events;
        }
    }

    public static BasicEvent getEvent(String caption, Date start, Date end) {
        BasicEvent event = new BasicEvent();
        event.setCaption(caption);
        event.setStart(start);
        event.setEnd(end);

        return event;
    }
}