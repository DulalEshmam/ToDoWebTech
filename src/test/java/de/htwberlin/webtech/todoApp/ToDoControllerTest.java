package de.htwberlin.webtech.todoApp;

import de.htwberlin.webtech.todoApp.service.TodoService;
import de.htwberlin.webtech.todoApp.web.ToDoController;
import de.htwberlin.webtech.todoApp.web.api.ToDoApi;
import de.htwberlin.webtech.todoApp.web.api.TodoManipulationRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ToDoControllerTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private ToDoController toDoController;

    @Test
    public void testFetchTodosById() {
        ToDoApi toDoApi = new ToDoApi(1L, "Test", "In Progress", new Date());
        when(todoService.findById(anyLong())).thenReturn(toDoApi);

        ResponseEntity<ToDoApi> response = toDoController.fetchTodosById(1L);

        assertNotNull(response.getBody());
        assertEquals(toDoApi.getTitle(), response.getBody().getTitle());
    }

    @Test
    public void testCreateTodo() {
        ToDoApi toDoApi = new ToDoApi(1L, "Test", "In Progress", new Date());
        when(todoService.create(any(TodoManipulationRequest.class))).thenReturn(toDoApi);

        ResponseEntity<List<ToDoApi>> response = toDoController.createTodo(new TodoManipulationRequest("Test", "In Progress", new Date()));

        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    public void testFetchTodos() {
        ToDoApi toDoApi = new ToDoApi(1L, "Test", "In Progress", new Date());
        when(todoService.findAll()).thenReturn(Collections.singletonList(toDoApi));

        ResponseEntity<List<ToDoApi>> response = toDoController.fetchTodos();

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(toDoApi.getTitle(), response.getBody().get(0).getTitle());
    }

    @Test
    public void testUpdateTodo() {
        ToDoApi toDoApi = new ToDoApi(1L, "Test", "In Progress", new Date());
        when(todoService.update(anyLong(), any(TodoManipulationRequest.class))).thenReturn(toDoApi);

        ResponseEntity<ToDoApi> response = toDoController.updateTodo(1L, new TodoManipulationRequest("Test", "In Progress", new Date()));

        assertNotNull(response.getBody());
        assertEquals(toDoApi.getTitle(), response.getBody().getTitle());
    }

    @Test
    public void testDeleteTodo() {
        when(todoService.deleteById(anyLong())).thenReturn(true);

        ResponseEntity<Void> response = toDoController.deleteTodo(1L);

        assertEquals(200, response.getStatusCodeValue());
    }

}
