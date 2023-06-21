package de.htwberlin.webtech.todoApp.web.api;

import de.htwberlin.webtech.todoApp.entity.TodoEntity;

import java.util.Set;

public class UserManipulationRequest {


    private String name;
    private String username;
    private String password;
    private Set<TodoEntity> todos;

    public UserManipulationRequest(String name, String username, String password, Set<TodoEntity> todos) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.todos = todos;
    }
    public UserManipulationRequest() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Set<TodoEntity> getTodos() {
        return todos;
    }

    public void setTodos(Set<TodoEntity> todos) {
        this.todos = todos;
    }

}
