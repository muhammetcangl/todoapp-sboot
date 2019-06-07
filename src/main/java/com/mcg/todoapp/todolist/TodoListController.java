package com.mcg.todoapp.todolist;

import com.mcg.todoapp.exception.AppException;
import com.mcg.todoapp.payload.ApiResponse;
import com.mcg.todoapp.security.CurrentUser;
import com.mcg.todoapp.security.UserPrincipal;
import com.mcg.todoapp.user.User;
import com.mcg.todoapp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/list")
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<TodoList>> findAllByOwner(@CurrentUser UserPrincipal currentUser) {
        return new ResponseEntity<>(todoListService.findAllByOwnerId(currentUser.getId()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoList> findById(@PathVariable Long id){
        return new ResponseEntity<>(todoListService.findById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TodoList> saveTodoList(@RequestBody TodoList todoList, @CurrentUser UserPrincipal currentUser) {
        User user = userRepository.findById(currentUser.getId()).orElseThrow(() -> new AppException("User not found"));
        todoList.setOwner(user);
        return new ResponseEntity<>(todoListService.save(todoList),HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<TodoList> updateTodoList(@RequestBody TodoList todoList, @PathVariable Long id) {
        TodoList todoList_ = todoListService.findById(id);
        todoList_.setTitle(todoList.getTitle());
        return new ResponseEntity<>(todoListService.save(todoList_), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodoList(@PathVariable Long id) {
        todoListService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
