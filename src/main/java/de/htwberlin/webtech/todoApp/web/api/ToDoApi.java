package de.htwberlin.webtech.todoApp.web.api;

public class ToDoApi {

    private long id;
    private String title;
    private String description;
    private boolean done;
    private long userId;

    public ToDoApi(long id, String title, String description, boolean done, long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = done;
        this.userId = userId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
