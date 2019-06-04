package com.mcg.todoapp.todolist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoListService {

    private final TodoListRepository todoListRepository;

    public List<TodoList> findAllByOwnerId(Long ownerId){
        return todoListRepository.findAllByOwner(ownerId).orElse(null);
    }
}
