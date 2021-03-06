package sigmascheduler.userinterface;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.annotation.WebServlet;
import sigmascheduler.engine.SigmaSchedulerSession;
import sigmascheduler.userinterface.views.DashboardView;
import sigmascheduler.userinterface.views.EventCalendar;
import sigmascheduler.userinterface.views.LoginView;
import sigmascheduler.userinterface.views.MyEventsView;
import sigmascheduler.userinterface.views.SidebarView;

@Theme("dashboard")
@SuppressWarnings("serial")
@Title("Sigma Scheduler")
public class SigmaSchedulerUI extends UI {
    
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = SigmaSchedulerUI.class, widgetset = "sigmascheduler.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }
    
    private HashMap<String, Class<? extends View>> routes = new HashMap<String, Class<? extends View>>() {
        {
            put("", LoginView.class);
            put("/dashboard", DashboardView.class);
            put("/eventcalendar", EventCalendar.class);
            put("/myevents", MyEventsView.class);
        }
    };
    
    private String[] destinations = new String[] {
        "Dashboard",
        "Event Calendar",
        "My Events",
    };
    
    private HashMap<String, Button> viewNameToMenuButton = new HashMap<String, Button>();

    private Navigator nav;
    
    private CssLayout root = new CssLayout();
    private CssLayout menu = new CssLayout();
    private CssLayout content = new CssLayout();
    
    private VerticalLayout loginLayout;
    
    private SigmaSchedulerSession session;

    @Override
    protected void init(VaadinRequest request) {
        session = new SigmaSchedulerSession();
        displayLogin();
    }
    
    public void displayLogin() {
        root.removeAllComponents();
        
        setContent(root);
        root.addStyleName("root");
        root.setSizeFull();
        
        Label bg = new Label();
        bg.setSizeUndefined();
        bg.addStyleName("login-bg");
        root.addComponent(bg);

        addStyleName("login");
        loginLayout = new LoginView();
        root.addComponent(loginLayout);
    }
    
    public void displayMain() {
        nav = new Navigator(this, content);

        for (String route : routes.keySet()) nav.addView(route, routes.get(route));

        removeStyleName("login");
        root.removeComponent(loginLayout);
        
        final HorizontalLayout fullLayout = new HorizontalLayout();
        fullLayout.setSizeFull();
        fullLayout.addStyleName("main-view");
        fullLayout.addComponent(new SidebarView(menu));
        
        // Content
        fullLayout.addComponent(content);
        content.setSizeFull();
        content.addStyleName("view-content");
        fullLayout.setExpandRatio(content, 1);
        
        root.addComponent(fullLayout);

        menu.removeAllComponents();

        for (final String name : destinations) {
            Button b = new NativeButton(name);
            final String target = name.replaceAll(" ", "").toLowerCase();
            b.addStyleName("icon-" + target);
            b.addClickListener(new ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    navigateTo("/" + target);
                }
            });

            menu.addComponent(b);

            viewNameToMenuButton.put("/" + target, b);
        }
        menu.addStyleName("menu");
        menu.setHeight("100%");
        
        viewNameToMenuButton.get("/dashboard").setHtmlContentAllowed(true);
        viewNameToMenuButton.get("/dashboard").setCaption(
                "Dashboard<span class=\"badge\">17</span>");

        String f = Page.getCurrent().getUriFragment();
        if (f != null && f.startsWith("!")) {
            f = f.substring(1);
        }
        if (f == null || f.equals("") || f.equals("/")) {
            nav.navigateTo("/dashboard");
            menu.getComponent(0).addStyleName("selected");
//            helpManager.showHelpFor(DashboardView.class);
        } else {
            nav.navigateTo(f);
//            helpManager.showHelpFor(routes.get(f));
            viewNameToMenuButton.get(f).addStyleName("selected");
        }
    }
    
    public void navigateTo(String target) {
        clearMenuSelection();
        viewNameToMenuButton.get(target).addStyleName("selected");
        if (!nav.getState().equals(target))
            nav.navigateTo(target);
        
    }
    
    private void clearMenuSelection() {
        for (Iterator<Component> it = menu.iterator(); it.hasNext();) {
            Component next = it.next();
            if (next instanceof NativeButton) next.removeStyleName("selected");
        }
    }
    
    public SigmaSchedulerSession getSigmaSchedulerSession() {
        return session;
    }
    
}
