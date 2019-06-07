package com.mcg.todoapp.todolist;

import com.mcg.todoapp.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoListService {

    private final TodoListRepository todoListRepository;

    @Transactional
    public List<TodoList> findAllByOwnerId(Long ownerId) {
        return todoListRepository.findAllByOwnerId(ownerId).orElse(null);
    }

    @Transactional
    public TodoList findById(Long id) {
        return todoListRepository.findById(id).orElseThrow(() -> new AppException("Todo List not found"));
    }

    @Transactional
    public List<TodoList> findAll(){
        return todoListRepository.findAll(new Sort(Sort.Direction.ASC,"id"));
    }

    @Transactional
    public TodoList save(TodoList todoList){
        return todoListRepository.save(todoList);
    }

    @Transactional
    public void delete(Long id) {
        todoListRepository.deleteById(id);
    }
}
