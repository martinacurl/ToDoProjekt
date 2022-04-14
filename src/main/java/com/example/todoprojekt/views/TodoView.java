package com.example.todoprojekt.views;

import com.example.todoprojekt.security.PrincipalUtils;
import com.example.todoprojekt.services.TodoService;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PermitAll
@Route(value = "/todos", layout = AppView.class)
public class TodoView extends VerticalLayout {

    TodoService todoService;

    public TodoView(TodoService todoService){
        this.todoService = todoService;
        setAlignItems(Alignment.CENTER);

        add(new H2("Your toDos"));

        todoService.findToDoByAppUserUsername(PrincipalUtils.getName()).forEach(todo -> {

            HorizontalLayout todoLayout = new HorizontalLayout();

            Checkbox done = new Checkbox();
            done.setValue(todo.isDone());
            done.addValueChangeListener(evt -> {
                todo.setDone(evt.getValue());
                todoService.updateTodoById(todo.getId(), todo);});

            done.setLabel(todo.getTask());

            add(done, new Hr());
            add(todoLayout);
        });
    }
}
