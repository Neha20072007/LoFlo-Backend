package com.mahendra.todoapp.services;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahendra.todoapp.dto.TodoItemDTO;
import com.mahendra.todoapp.models.TodoItem;
import com.mahendra.todoapp.models.User;
import com.mahendra.todoapp.repositories.TodoRepository;
import com.mahendra.todoapp.repositories.UserRepository;

@Service
public class TodoItemService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public Iterable<TodoItem> getAll() {
        return todoRepository.findAll();
    }

    public List<TodoItemDTO> getTodosByUserId(long userId) {
        return todoRepository.findByUserId(userId).stream()
            .map(todo -> new TodoItemDTO(
                todo.getId(),
                todo.getDescription(),
                todo.getIsComplete(),
                todo.getCreatedAt(),
                todo.getUpdatedAt(),
                todo.getUser().getId()  // Assuming you need to include the userId
            ))
            .collect(Collectors.toList());
    }

    public TodoItem save(TodoItem todoItem) {
        if (!Objects.isNull(todoItem.getId())) {
            todoItem.setUpdatedAt(Instant.now());
        } else {
            todoItem.setCreatedAt(Instant.now());
        }
        return todoRepository.save(todoItem);
    }

    public void delete(TodoItem todoItem) {
        todoRepository.delete(todoItem);
    }

    public Optional<TodoItem> getById(long id) {
        return todoRepository.findById(id);
    }

    public TodoItem createTodoForUser(Long userId, TodoItem todoItem) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new RuntimeException("User not found"));
        todoItem.setUser(user);
        return save(todoItem);
    }
}
