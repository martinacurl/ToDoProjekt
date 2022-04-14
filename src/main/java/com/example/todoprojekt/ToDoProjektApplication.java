package com.example.todoprojekt;

import com.example.todoprojekt.entities.AppUser;
import com.example.todoprojekt.entities.Todo;
import com.example.todoprojekt.repositories.AppUserRepository;
import com.example.todoprojekt.repositories.TodoRepository;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class ToDoProjektApplication implements CommandLineRunner {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ToDoProjektApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        AppUser Martina = new AppUser("Martina", passwordEncoder.encode("pass"));
        AppUser Björn = new AppUser("Björn", passwordEncoder.encode("pass"));
        AppUser Viktor = new AppUser("Viktor", passwordEncoder.encode("pass"));
        appUserRepository.saveAll(List.of(Martina, Björn, Viktor));

        Todo toDo = new Todo("start projekt", true, Martina);
        Todo toDo1 = new Todo("finish projekt", false, Martina);
        Todo toDo2 = new Todo("my first todo", true, Björn);
        Todo todo3 = new Todo("Buy milk", false, Björn);
        Todo todo4 = new Todo("Write report", false, Martina);

        todoRepository.saveAll(List.of(toDo,toDo1, toDo2, todo3, todo4));

    }
}
