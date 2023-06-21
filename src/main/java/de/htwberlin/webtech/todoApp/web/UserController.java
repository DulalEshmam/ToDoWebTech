package de.htwberlin.webtech.todoApp.web;

import de.htwberlin.webtech.todoApp.service.UserService;
import de.htwberlin.webtech.todoApp.web.api.UserApi;
import de.htwberlin.webtech.todoApp.web.api.UserManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/userApi/v2/users/{userId}")
    public ResponseEntity<UserApi> fetchUserById(@PathVariable long userId) {
        var user = userService.findById(userId);
        return user != null? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/userApi/v2/users")
    public ResponseEntity<List<UserApi>> createUser(@RequestBody UserManipulationRequest request) {
        var user = userService.create(request);
        URI uri = URI.create("/userApi/v2/users/" + user.getUserId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(path = "/userApi/v2/users")
    public ResponseEntity<List<UserApi>> fetchUsers() {
        var user = userService.findAll();
        return ResponseEntity.ok(user);
    }

    @PutMapping(path = "/userApi/v2/users/{userId}")
    public ResponseEntity<UserApi> updateTodo(@PathVariable Long userId, @RequestBody UserManipulationRequest request) {
        var user = userService.update(userId, request);
        return user != null? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/userApi/v1/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        var successful = userService.deleteById(userId);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
