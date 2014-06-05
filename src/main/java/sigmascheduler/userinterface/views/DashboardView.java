package sigmascheduler.userinterface.views;

import com.vaadin.data.Property;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.Table.RowHeaderMode;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.text.DecimalFormat;
import sigmascheduler.engine.SigmaSchedulerException;
import sigmascheduler.engine.SigmaSchedulerSession;
import sigmascheduler.userinterface.SigmaSchedulerUI;

/**
 *
 * @author Dominik
 */
public class DashboardView extends VerticalLayout implements View {

    private Table t;
    private SigmaSchedulerSession session = ((SigmaSchedulerUI)UI.getCurrent()).getSigmaSchedulerSession();

    public DashboardView() {
        setSizeFull();
        addStyleName("dashboard-view");

        HorizontalLayout top = new HorizontalLayout();
        top.setWidth("100%");
        top.setSpacing(true);
        top.addStyleName("toolbar");
        addComponent(top);
        final Label title = new Label("Dashboard");
        title.setSizeUndefined();
        title.addStyleName("h1");
        top.addComponent(title);
        top.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
        top.setExpandRatio(title, 1);

        Button notify = new Button("2");
        notify.setDescription("Notifications (2 unread)");
        // notify.addStyleName("borderless");
        notify.addStyleName("notifications");
        notify.addStyleName("unread");
        notify.addStyleName("icon-only");
        notify.addStyleName("icon-bell");
        notify.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                event.getButton().removeStyleName("unread");
                event.getButton().setDescription("Notifications");

                if (notifications != null && notifications.getUI() != null)
                    notifications.close();
                else {
                    buildNotifications(event);
                    getUI().addWindow(notifications);
                    notifications.focus();
                    ((CssLayout) getUI().getContent())
                            .addLayoutClickListener(new LayoutClickListener() {
                                @Override
                                public void layoutClick(LayoutClickEvent event) {
                                    notifications.close();
                                    ((CssLayout) getUI().getContent())
                                            .removeLayoutClickListener(this);
                                }
                            });
                }

            }
        });
        top.addComponent(notify);
        top.setComponentAlignment(notify, Alignment.MIDDLE_LEFT);

        HorizontalLayout row = new HorizontalLayout();
        row.setSizeFull();
        row.setMargin(new MarginInfo(true, true, false, true));
        row.setSpacing(true);
        addComponent(row);
        setExpandRatio(row, 1);
        
        GridLayout textInfos = new GridLayout(2,3);
        textInfos.setSpacing(true);
        textInfos.setMargin(true);
        Label username = new Label(session.getUser().getName());
        username.setStyleName("h2");
        Label stat1d = new Label("Voted Events:");
        Label stat1v = new Label("0");
        Label stat2d = new Label("Created Events:");
        Label stat2v;
        try {
            stat2v = new Label(session.getEventManager().getManagedEvents(session.getUser()).size() + "");
        } catch (SigmaSchedulerException ex) {
            stat2v = new Label("0");
        }
        textInfos.addComponent(username,0,0);
        textInfos.addComponent(stat1d,0,1);
        textInfos.addComponent(stat1v,1,1);
        textInfos.addComponent(stat2d,0,2);
        textInfos.addComponent(stat2v,1,2);
        CssLayout info = new CssLayout();
        info.addStyleName("layout-panel");
        info.addStyleName("plain");
        info.setSizeFull();
        Image avatar = session.getUserManager().getAvatar();
        avatar.setStyleName("clear-panel");
        avatar.setHeight("100%");
        info.addComponent(avatar);
        info.addComponent(textInfos);
        row.addComponent(info);
        
        CssLayout date = new CssLayout();
        date.addStyleName("layout-panel");
        date.addStyleName("plain");
        date.setSizeFull();
        InlineDateField dateField = new InlineDateField();
        dateField.setStyleName("reindeer");
        date.addComponent(dateField);
        row.addComponent(date);

        row = new HorizontalLayout();
        row.setMargin(true);
        row.setSizeFull();
        row.setSpacing(true);
        addComponent(row);
        setExpandRatio(row, 2);

        t = new Table() {
            @Override
            protected String formatPropertyValue(Object rowId, Object colId,
                    Property<?> property) {
                if (colId.equals("Revenue")) {
                    if (property != null && property.getValue() != null) {
                        Double r = (Double) property.getValue();
                        String ret = new DecimalFormat("#.##").format(r);
                        return "$" + ret;
                    } else {
                        return "";
                    }
                }
                return super.formatPropertyValue(rowId, colId, property);
            }
        };
        t.setCaption("Next Events");

        t.setWidth("100%");
        t.setPageLength(0);
        t.addStyleName("plain");
        t.addStyleName("borderless");
        t.setSortEnabled(false);
        t.setColumnAlignment("Revenue", Align.RIGHT);
        t.setRowHeaderMode(RowHeaderMode.INDEX);

        row.addComponent(createPanel(t));

    }

    private CssLayout createPanel(Component content) {
        CssLayout panel = new CssLayout();
        panel.addStyleName("layout-panel");
        panel.setSizeFull();

        Button configure = new Button();
        configure.addStyleName("configure");
        configure.addStyleName("icon-cog");
        configure.addStyleName("icon-only");
        configure.addStyleName("borderless");
        configure.setDescription("Configure");
        configure.addStyleName("small");
        configure.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Notification.show("Not implemented in this demo");
            }
        });
        panel.addComponent(configure);

        panel.addComponent(content);
        return panel;
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }

    Window notifications;

    private void buildNotifications(ClickEvent event) {
        notifications = new Window("Notifications");
        VerticalLayout l = new VerticalLayout();
        l.setMargin(true);
        l.setSpacing(true);
        notifications.setContent(l);
        notifications.setWidth("300px");
        notifications.addStyleName("notifications");
        notifications.setClosable(false);
        notifications.setResizable(false);
        notifications.setDraggable(false);
        notifications.setPositionX(event.getClientX() - event.getRelativeX());
        notifications.setPositionY(event.getClientY() - event.getRelativeY());
        notifications.setCloseShortcut(KeyCode.ESCAPE, null);
    }

}
