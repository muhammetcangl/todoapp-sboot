package com.mcg.todoapp.todoitem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoItemRequestModel {
    private String title;
    private String description;
    private Boolean done;
    private Long depndntTodoItemId;
}
