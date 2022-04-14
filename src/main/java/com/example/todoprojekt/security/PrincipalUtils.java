package com.example.todoprojekt.security;

import com.example.todoprojekt.entities.AppUser;
import com.example.todoprojekt.repositories.AppUserRepository;
import com.example.todoprojekt.views.AppView;
import com.example.todoprojekt.views.HelloView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class PrincipalUtils {

    @Autowired
    AppUserRepository appUserRepository;

    public static String getName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static boolean isAuthenticated(){
        return !SecurityContextHolder.getContext().getAuthentication().getName().equalsIgnoreCase("anonymousUser");
    }

    public static void logOut(){
        UI.getCurrent().navigate(HelloView.class);
        new SecurityContextLogoutHandler().logout(VaadinServletRequest.getCurrent().getHttpServletRequest(), null, null);
    }

    public AppUser getLoggedinAppUserFromPrincipal(){
        return appUserRepository.findAppUserByUsername(getName()).orElseThrow();
    }


}
