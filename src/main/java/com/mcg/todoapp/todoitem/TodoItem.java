package com.mcg.todoapp.todoitem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mcg.todoapp.todolist.TodoList;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "todo_item")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "done")
    private Boolean done;

    @OneToOne
    @JoinColumn(name = "depndnt_todo_item")
    @JsonIgnore
    private TodoItem depndntTodoItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_list_id")
    @JsonIgnore
    private TodoList todoList;
}
