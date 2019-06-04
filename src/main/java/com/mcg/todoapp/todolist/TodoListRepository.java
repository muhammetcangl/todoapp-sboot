package com.mcg.todoapp.todolist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {

    Optional<List<TodoList>> findAllByOwner(Long ownerId);
}
