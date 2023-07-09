package de.htwberlin.webtech.todoApp.web;

import de.htwberlin.webtech.todoApp.service.TodoService;
import de.htwberlin.webtech.todoApp.web.api.ToDoApi;
import de.htwberlin.webtech.todoApp.web.api.TodoManipulationRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class ToDoController {
    private final TodoService todoService;

    public ToDoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @GetMapping(path = "/api/v1/todos/{id}")
    public ResponseEntity<ToDoApi> fetchTodosById(@PathVariable long id) {
        var todos = todoService.findById(id);
        return todos != null? ResponseEntity.ok(todos) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/todos")
    public ResponseEntity<List<ToDoApi>> createTodo(@Valid @RequestBody TodoManipulationRequest request) {
        var todo = todoService.create(request);
        URI uri = URI.create("/api/v1/users/" + todo.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(path = "/api/v1/todos")
    public ResponseEntity<List<ToDoApi>> fetchTodos() {
        var todo = todoService.findAll();
        return ResponseEntity.ok(todo);
    }

    @PutMapping(path = "/api/v1/todos/{id}")
    public ResponseEntity<ToDoApi> updateTodo(@PathVariable Long id, @RequestBody TodoManipulationRequest request) {
        var todo = todoService.update(id, request);
        return todo != null? ResponseEntity.ok(todo) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        var successful = todoService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
