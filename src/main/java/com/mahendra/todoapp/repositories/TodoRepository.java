package com.mahendra.todoapp.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahendra.todoapp.models.TodoItem;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem, Long> {

   List<TodoItem> findByUserId(long userId);
}
