package com.green.todoapp;

import com.green.todoapp.model.TodoInsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo")
public class TodoContorller {

    private TodoService service;

    @Autowired
    public TodoContorller(TodoService service) {
        this.service = service;
    }

    @PostMapping
    public int postTodo(@RequestBody TodoInsDto dto) {
        return service.insTodo(dto);
    }
}
