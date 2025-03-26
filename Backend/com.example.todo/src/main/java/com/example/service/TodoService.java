package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Todo;
import com.example.repository.TodoRepository;

@Service
public class TodoService {
	private final TodoRepository repository;
	public TodoService(TodoRepository repository) {
		this.repository=repository;
	}
	public List<Todo> getAllTodos(){
		return repository.findAll();
	}
	public Todo addTodo(Todo todo) {
		return repository.save(todo);
	}
	public void deleteTodoById(Long id) {
		repository.deleteById(id);
	}
	
}
