package com.mcg.todoapp.todoitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

    Optional<List<TodoItem>> findByTodoList_Id(Long listId);

    Optional<List<TodoItem>> findAllByDepndntTodoItem_Id(Long id);
}
