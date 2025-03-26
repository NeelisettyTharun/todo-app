package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Todo;
import com.example.service.TodoService;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "http://localhost:5173")
public class TodoController {

    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Todo> getTodos() {
        return service.getAllTodos();
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = service.addTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) {
        logger.info("Received DELETE request for ID: {}", id);
        try {
            service.deleteTodoById(id);
            return ResponseEntity.ok("Todo deleted successfully");
        } catch (Exception e) {
            logger.error("Error deleting todo with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo not found");
        }
    }
}
