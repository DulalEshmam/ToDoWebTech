package de.htwberlin.webtech.todoApp.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "todos")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String status;

    private Date date;

    public TodoEntity() {}

    public TodoEntity(String title, String status, Date date) {
        this.title = title;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
