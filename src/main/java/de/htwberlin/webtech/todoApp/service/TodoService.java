package de.htwberlin.webtech.todoApp.service;

import de.htwberlin.webtech.todoApp.entity.TodoEntity;
import de.htwberlin.webtech.todoApp.repository.TodoRepository;
import de.htwberlin.webtech.todoApp.web.api.ToDoApi;
import de.htwberlin.webtech.todoApp.web.api.TodoManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<ToDoApi> findAll() {
        List<TodoEntity> todos = todoRepository.findAll();
        return todos.stream()
                    .map(this :: transformEntity)
                    .collect(Collectors.toList());
    }

    public ToDoApi findById(long id) {
        var todoEntity = todoRepository.findById(id);
        return todoEntity.map(this :: transformEntity).orElse(null);
    }

    public ToDoApi create(TodoManipulationRequest request) {
        var todoEntity = new TodoEntity(request.getTitle(), request.getStatus(), request.getDate());
        todoEntity = todoRepository.save(todoEntity);
        return transformEntity(todoEntity);
    }

    private ToDoApi transformEntity(TodoEntity todoEntity) {
        return new ToDoApi(
                todoEntity.getId(),
                todoEntity.getTitle(),
                todoEntity.getStatus(),
                todoEntity.getDate()
        );
    }

    public ToDoApi update(long id, TodoManipulationRequest request) {
        var todoEntityOptional = todoRepository.findById(id);
        if (todoEntityOptional.isEmpty()) {
            return null;
        }
        var todoEntity = todoEntityOptional.get();
        todoEntity.setTitle(request.getTitle());
        todoEntity.setStatus(request.getStatus());
        todoEntity.setDate(request.getDate());
        todoEntity = todoRepository.save(todoEntity);
        return transformEntity(todoEntity);
    }

    public boolean deleteById(long id) {
        if (!todoRepository.existsById(id)) {
            return false;
        }
        todoRepository.deleteById(id);
        return true;
    }

}
