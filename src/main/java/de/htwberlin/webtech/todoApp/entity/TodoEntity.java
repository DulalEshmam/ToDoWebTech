package de.htwberlin.webtech.todoApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private boolean done;
    @ManyToOne
    private UserEntity user;

    public TodoEntity() {}

    public TodoEntity(String title, String description, boolean done, UserEntity user) {
        this.title = title;
        this.description = description;
        this.done = done;
        this.user = user;
    }




}
