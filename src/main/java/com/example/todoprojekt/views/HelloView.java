package com.example.todoprojekt.views;

import com.example.todoprojekt.security.PrincipalUtils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@Route(value = "/", layout = AppView.class)
public class HelloView extends VerticalLayout {

    public HelloView() {
        add(new H2("Welcome to-Much toDo"));
        Paragraph paragraph = new Paragraph("If you, as all of us have Much toDo, join us and make your life much easier.");

        Button loginButton = new Button("Login", evt -> UI.getCurrent().navigate(LoginView.class));
        loginButton.addThemeVariants(ButtonVariant.LUMO_LARGE);

        if(!PrincipalUtils.isAuthenticated()){
            add(loginButton, paragraph);
        }

        setAlignItems(Alignment.CENTER);
    }

}
