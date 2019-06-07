package com.mcg.todoapp.todolist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mcg.todoapp.todoitem.TodoItem;
import com.mcg.todoapp.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "todo_list")
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(mappedBy = "todoList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TodoItem> todoItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "owner_user_id")
    @JsonIgnore
    private User owner; // listeler kullanicilara bagli

}
