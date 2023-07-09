package de.htwberlin.webtech.todoApp;
import de.htwberlin.webtech.todoApp.entity.TodoEntity;
import de.htwberlin.webtech.todoApp.repository.TodoRepository;
import de.htwberlin.webtech.todoApp.service.TodoService;
import de.htwberlin.webtech.todoApp.web.api.TodoManipulationRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.Collections;
import java.util.Date;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    public void testFindAll() {
        TodoEntity todoEntity = new TodoEntity("Test", "In Progress", new Date());
        when(todoRepository.findAll()).thenReturn(Collections.singletonList(todoEntity));

        var todos = todoService.findAll();

        assertEquals(1, todos.size());
        assertEquals(todoEntity.getTitle(), todos.get(0).getTitle());
    }

    @Test
    public void testFindById() {
        TodoEntity todoEntity = new TodoEntity("Test", "In Progress", new Date());
        when(todoRepository.findById(anyLong())).thenReturn(Optional.of(todoEntity));

        var todo = todoService.findById(1L);

        assertNotNull(todo);
        assertEquals(todoEntity.getTitle(), todo.getTitle());
    }

    @Test
    public void testCreate() {
        TodoEntity todoEntity = new TodoEntity("Test", "In Progress", new Date());
        when(todoRepository.save(any(TodoEntity.class))).thenReturn(todoEntity);

        var todo = todoService.create(new TodoManipulationRequest("Test", "In Progress", new Date()));

        assertNotNull(todo);
        assertEquals(todoEntity.getTitle(), todo.getTitle());
    }

    @Test
    public void testUpdate() {
        TodoEntity todoEntity = new TodoEntity("Test", "In Progress", new Date());
        when(todoRepository.findById(anyLong())).thenReturn(Optional.of(todoEntity));
        when(todoRepository.save(any(TodoEntity.class))).thenReturn(todoEntity);

        var todo = todoService.update(1L, new TodoManipulationRequest("Test", "In Progress", new Date()));

        assertNotNull(todo);
        assertEquals(todoEntity.getTitle(), todo.getTitle());
    }

    @Test
    public void testDeleteById() {
        when(todoRepository.existsById(anyLong())).thenReturn(true);

        var isDeleted = todoService.deleteById(1L);

        assertTrue(isDeleted);
        verify(todoRepository, times(1)).deleteById(anyLong());
    }

}
