package com.example.todoprojekt.views;

import com.example.todoprojekt.entities.Todo;
import com.example.todoprojekt.security.PrincipalUtils;
import com.example.todoprojekt.services.TodoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PermitAll
@Route(value = "/editor", layout = AppView.class)
public class EditorView extends VerticalLayout {

    TodoService todoService;
    Grid<Todo> grid = new Grid<>(Todo.class, false);
    PrincipalUtils principalUtils;

    public EditorView(TodoService todoService, PrincipalUtils principalUtils){
        this.todoService = todoService;
        this.principalUtils = principalUtils;

        add(new H2("Do something with your toDos"));

        TodoForm newForm = new TodoForm(todoService, this, principalUtils);
        newForm.setTodo();
        add(newForm);

        grid.setItems(todoService.findTodoByAppUserUsername(PrincipalUtils.getName()));

        grid.addComponentColumn(toDo -> {

            Checkbox done = new Checkbox();
            done.setValue(toDo.isDone());
            done.addValueChangeListener(evt -> {
                toDo.setDone(evt.getValue());
                todoService.updateTodoById(toDo.getId(), toDo);});

            TextField task = new TextField();
            task.setValue(toDo.getTask());
            task.setWidth("80%");

            Button update = new Button("Update", event -> {
                Notification.show("Todo successfully changed!");
                toDo.setTask(task.getValue());
                todoService.updateTodoById(toDo.getId(), toDo);
                updateItems();
            });

            Button remove = new Button(new Icon(VaadinIcon.CLOSE_CIRCLE), event -> {
                todoService.deleteTodoById(toDo.getId());
                updateItems();
            });
            remove.addThemeVariants(ButtonVariant.LUMO_ERROR);

            return new HorizontalLayout(done, task, update, remove);
        });

        add(grid);

    }

    public void updateItems() {
        grid.setItems(todoService.findToDoByAppUserUsername(PrincipalUtils.getName()));
    }
}
