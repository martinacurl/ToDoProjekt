package com.example.todoprojekt.views;

import com.example.todoprojekt.security.PrincipalUtils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;

public class AppView extends AppLayout {

    public AppView(){

        HorizontalLayout navbarLayout = new HorizontalLayout();

        H1 navbarTitle = new H1("Much toDo");

        Button loginButton = new Button("Login", evt -> UI.getCurrent().navigate(LoginView.class));
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        String name = PrincipalUtils.getName();
        Avatar avatar = new Avatar(name);
        avatar.addThemeVariants(AvatarVariant.LUMO_LARGE);

        MenuBar menuBar = new MenuBar();
        menuBar.addThemeVariants(MenuBarVariant.LUMO_TERTIARY_INLINE,
                MenuBarVariant.LUMO_LARGE);
        MenuItem menuItem = menuBar.addItem(avatar);

        SubMenu subMenu = menuItem.getSubMenu();
        subMenu.addItem(PrincipalUtils.getName());
        subMenu.addItem("Settings", clickEvent -> Notification.show("Coming soon..."));
        subMenu.addItem("Help");
        subMenu.addItem("LogOut", clickEvent -> PrincipalUtils.logOut());

        Tabs tabs = new Tabs();
        tabs.add(
                new Tab(new RouterLink("Your toDos", TodoView.class)),
                new Tab(new RouterLink("Edit toDos", EditorView.class)),
                new Tab(new RouterLink("Hello", HelloView.class))
        );

        Tabs tabsSeenByAll = new Tabs();
        tabsSeenByAll.add(new Tab(new RouterLink("Hello", HelloView.class)));

        navbarLayout.add(navbarTitle);

        if(PrincipalUtils.isAuthenticated()){
            navbarLayout.add(tabs);
        } else {
            navbarLayout.add(tabsSeenByAll);
        }

        navbarLayout.add(PrincipalUtils.isAuthenticated() ? menuBar : loginButton);

        navbarLayout.setWidthFull();
        navbarLayout.setMargin(true);
        navbarLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        navbarLayout.setAlignItems(FlexComponent.Alignment.BASELINE);

        addToNavbar(navbarLayout);
    }
}



