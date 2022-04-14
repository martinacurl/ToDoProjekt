package com.example.todoprojekt.views;

import com.example.todoprojekt.entities.Todo;
import com.example.todoprojekt.security.PrincipalUtils;
import com.example.todoprojekt.services.TodoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

public class TodoForm extends FormLayout {

    TextField task = new TextField();
    Button saveButton = new Button(new Icon(VaadinIcon.PLUS));

    Binder<Todo> binder = new BeanValidationBinder<>(Todo.class);
    TodoService todoService;
    EditorView editorView;
    PrincipalUtils principalUtils;

    public TodoForm (TodoService todoService, EditorView editorView, PrincipalUtils principalUtils){
        this.todoService = todoService;
        this.editorView = editorView;
        this.principalUtils = principalUtils;

        binder.bindInstanceFields(this);

        task.setPlaceholder("Add your toDo here");

        saveButton.addClickListener(evt -> onSave());
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(task, saveButton);
    }

    public void onSave() {
        Todo todo = binder.validate().getBinder().getBean();
        todoService.createTodo(todo);
        setTodo();
        editorView.updateItems();
    }

    public void setTodo() {
        Todo newTodo = new Todo();
        newTodo.setAppUser(principalUtils.getLoggedinAppUserFromPrincipal());
        binder.setBean(newTodo);
    }
}
