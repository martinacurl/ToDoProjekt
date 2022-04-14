package com.example.todoprojekt.repositories;

import com.example.todoprojekt.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByAppUserUsername(String username);
}
