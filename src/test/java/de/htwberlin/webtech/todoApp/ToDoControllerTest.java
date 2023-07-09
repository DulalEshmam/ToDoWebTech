package de.htwberlin.webtech.todoApp;

import de.htwberlin.webtech.todoApp.service.TodoService;
import de.htwberlin.webtech.todoApp.web.ToDoController;
import de.htwberlin.webtech.todoApp.web.api.ToDoApi;
import de.htwberlin.webtech.todoApp.web.api.TodoManipulationRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(ToDoController.class)
public class ToDoControllerTest {

    @MockBean
    private TodoService todoService;

    @Autowired
    private MockMvc mockMvc;



        @Test
        @DisplayName("should return found todos from todo service")
        void should_return_found_todos_from_todo_service() throws Exception {
            // given
            ToDoApi toDoApi = new ToDoApi(2L, "Test", "In Progress", new Date());
            when(todoService.findAll()).thenReturn(Collections.singletonList(toDoApi));

            // when
            mockMvc.perform(get("/api/v1/todos"))
                    // then
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()").value(1))
                    .andExpect(jsonPath("$[0].id").value(2))
                    .andExpect(jsonPath("$[0].title").value("Test"))
                    .andExpect(jsonPath("$[0].status").value("In Progress"));
        }

        @Test
        @DisplayName("should return 404 if todo is not found")
        void should_return_404_if_todo_is_not_found() throws Exception {
            // given
            when(todoService.findById(anyLong())).thenReturn(null);

            // when
            mockMvc.perform(get("/api/v1/todos/123"))
                    // then
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("should return 201 http status and Location header when creating a todo")
        void should_return_201_http_status_and_location_header_when_creating_a_todo() throws Exception {
            // given
            String todoToCreateAsJson = "{\"title\": \"Test\", \"status\":\"In Progress\", \"date\":\"2023-07-06T10:15:30\"}";
            ToDoApi toDoApi = new ToDoApi(123L, "Test", "In Progress", new Date());
            when(todoService.create(any(TodoManipulationRequest.class))).thenReturn(toDoApi);

            // when
            mockMvc.perform(
                            post("/api/v1/todos")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(todoToCreateAsJson)
                    )
                    // then
                    .andExpect(status().isCreated())
                    .andExpect(header().exists("Location"))
                    .andExpect(header().string("Location", "/api/v1/todos/" + toDoApi.getId()));
        }

        @Test
        @DisplayName("should validate create todo request")
        void should_validate_create_todo_request() throws Exception {
            // given
            String todoToCreateAsJson = "{\"title\": \"\", \"status\":\"\"}";

            // when
            mockMvc.perform(
                            post("/api/v1/todos")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(todoToCreateAsJson)
                    )
                    // then
                    .andExpect(status().isBadRequest());
        }
    }

