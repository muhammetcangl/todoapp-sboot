package com.mcg.todoapp.todoitem;

import com.mcg.todoapp.exception.AppException;
import com.mcg.todoapp.todolist.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class TodoItemController {

    @Autowired
    private TodoItemService todoItemService;

    @Autowired
    private TodoListRepository todoListRepository;

    @GetMapping
    public ResponseEntity<List<TodoItem>> findDependencyExist(@RequestParam("dpndntId") Long id) {
        return new ResponseEntity<>(todoItemService.findAllByDpndntId(id),HttpStatus.OK);
    }

    @GetMapping("/{listId}")
    public ResponseEntity<List<TodoItem>> findByListId(@PathVariable Long listId) {
        return new ResponseEntity<>(todoItemService.findByListId(listId), HttpStatus.OK);
    }

    @PostMapping("/{listId}")
    public ResponseEntity<TodoItem> addItemToList(@RequestBody TodoItemRequestModel todoItem, @PathVariable(name = "listId") Long listId) {
        TodoItem todoItem_ = new TodoItem();
        todoItem_.setTitle(todoItem.getTitle());
        todoItem_.setDescription(todoItem.getDescription());
        todoItem_.setTodoList(todoListRepository.findById(listId).orElseThrow(() -> new AppException("List not found")));
        todoItem_.setDone(Boolean.FALSE);
        if(todoItem.getDepndntTodoItemId() != null && todoItem.getDepndntTodoItemId() > 0){
            todoItem_.setDepndntTodoItem(todoItemService.findById(todoItem.getDepndntTodoItemId()));
        }
        return new ResponseEntity<>(todoItemService.addItemToList(todoItem_),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodoList(@PathVariable Long id) {
        todoItemService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
