package com.mcg.todoapp.todoitem;

import com.mcg.todoapp.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TodoItemService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Transactional
    public List<TodoItem> findByListId(Long listId) {
        return todoItemRepository.findByTodoList_Id(listId).orElse(null);
    }

    @Transactional
    public TodoItem findById(Long id) {
        return todoItemRepository.findById(id).orElseThrow(() -> new AppException("Item not found"));
    }

    @Transactional
    public TodoItem addItemToList(TodoItem todoItem) {
        return todoItemRepository.save(todoItem);
    }

    @Transactional
    public void delete(Long id) {
        todoItemRepository.deleteById(id);
    }

    @Transactional
    public List<TodoItem> findAllByDpndntId(Long id) {
        return todoItemRepository.findAllByDepndntTodoItem_Id(id).orElse(null);
    }

    @Transactional
    public List<TodoItem> getTodoItems() {
        return todoItemRepository.findAll();
    }
}
