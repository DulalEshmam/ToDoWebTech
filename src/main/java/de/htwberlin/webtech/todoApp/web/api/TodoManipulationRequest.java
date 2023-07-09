package de.htwberlin.webtech.todoApp.web.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class TodoManipulationRequest {
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;
    @NotBlank(message = "Status must not be blank")
    private String status;
    @NotNull(message = "Date must not be blank")
    private Date date;

    public TodoManipulationRequest(String title, String status, Date date) {
        this.title = title;
        this.status = status;
        this.date = date;
    }

    public TodoManipulationRequest() {}

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
