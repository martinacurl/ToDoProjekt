package com.example.todoprojekt.services;

import com.example.todoprojekt.entities.Todo;
import com.example.todoprojekt.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    public List<Todo> getAll(){
        return todoRepository.findAll();
    }

    public List<Todo> findTodoByAppUserUsername(String username){
        return todoRepository.findByAppUserUsername(username);
    }

    public Todo createTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public void deleteTodoById(int id){
        todoRepository.deleteById(id);
    }

    public Todo updateTodoById(int id, Todo todoOut){
        Todo todoIn = todoRepository.findById(id).orElseThrow();

        todoIn.setDone(todoOut.isDone());

        if(todoOut.getTask() != null){
            todoIn.setTask(todoOut.getTask());
        }

        return todoRepository.save(todoIn);
    }

    public List<Todo> findToDoByAppUserUsername(String username) {
        return todoRepository.findByAppUserUsername(username);
    }
}
