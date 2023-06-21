package de.htwberlin.webtech.todoApp.web.api;

import de.htwberlin.webtech.todoApp.entity.UserEntity;

import java.util.Date;

public class TodoManipulationRequest {
    private String title;
    private String description;
    private String status;
    private Date date;
    private UserEntity user;

    public TodoManipulationRequest(String title, String description, String status, Date date, UserEntity user) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.date = date;
        this.user = user;
    }

    public TodoManipulationRequest() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getUser() {
        return user;
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
