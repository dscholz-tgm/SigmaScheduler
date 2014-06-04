package sigmascheduler.userinterface.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import sigmascheduler.engine.SigmaSchedulerSession;
import sigmascheduler.userinterface.SigmaSchedulerUI;

/**
 *
 * @author Dominik
 */
public class SidebarView extends VerticalLayout implements View {
    
    public SidebarView(CssLayout menu) {
        super();
        
        final SigmaSchedulerSession session = ((SigmaSchedulerUI) UI.getCurrent()).getSigmaSchedulerSession();
        
        addStyleName("sidebar");
        setWidth(null);
        setHeight("100%");

        // Image
        Resource res = new ThemeResource("img/sigmascheduler_small.png");
        Image image = new Image(null, res);
        addComponent(image);

        // Main menu
        addComponent(menu);
        setExpandRatio(menu, 1);

        // User menu
        addComponent(new VerticalLayout() {
            {
                setSizeUndefined();
                addStyleName("user");
                Image profilePic = session.getUserManager().getAvatar();
                profilePic.setWidth("34px");
                addComponent(profilePic);
                Label userName = new Label(session.getUser().getName());
                userName.setSizeUndefined();
                addComponent(userName);

                MenuBar.Command cmd = new MenuBar.Command() {
                    @Override
                    public void menuSelected(
                        MenuBar.MenuItem selectedItem) {
                        Notification.show(selectedItem.getText() + ": Not implemented yet!");
                    }

                };
                MenuBar settings = new MenuBar();
                MenuBar.MenuItem settingsMenu = settings.addItem("",
                        null);
                settingsMenu.setStyleName("icon-cog");
                settingsMenu.addItem("Settings", cmd);
                settingsMenu.addItem("Preferences", cmd);
                settingsMenu.addSeparator();
                settingsMenu.addItem("My Account", cmd);
                addComponent(settings);

                Button exit = new NativeButton("Exit");
                exit.addStyleName("icon-cancel");
                exit.setDescription("Sign Out");
                addComponent(exit);
                exit.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        session.setUser(null);
                        ((SigmaSchedulerUI) getUI()).displayLogin();
                    }
                });
            }
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
    
}
