package de.htwberlin.webtech.todoApp.web.api;

import java.util.Date;

public class ToDoApi {

    private long id;
    private String title;
    private String status;

    private Date date;
    public ToDoApi(long id, String title, String status, Date date) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
