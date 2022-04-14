package com.example.todoprojekt.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank
    private String task;

    @Column
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "appuser_id")
    private AppUser appUser;

    public Todo() {
    }

    public Todo(String task, boolean  done, AppUser appUser) {
        this.task = task;
        this.done = done;
        this.appUser = appUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String description) {
        this.task = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

}
