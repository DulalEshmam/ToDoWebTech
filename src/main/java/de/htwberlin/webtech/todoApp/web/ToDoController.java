package de.htwberlin.webtech.todoApp.web;

import de.htwberlin.webtech.todoApp.web.api.ToDoApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ToDoController {
    private List<ToDoApi> todos;

    public ToDoController() {
        todos = new ArrayList<>();
        todos.add(new ToDoApi(1, "Test", "Test", false, 1));
        todos.add(new ToDoApi(2, "Test2", "Test2", false, 1));
    }
    @GetMapping(path = "/api/v1/todos")
    public ResponseEntity<List<ToDoApi>> fetchTodos() {
        return ResponseEntity.ok(todos);
    }

}
