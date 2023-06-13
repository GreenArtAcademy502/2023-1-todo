package com.green.todoapp;

import com.green.todoapp.model.TodoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoMapper {
    void insTodo(TodoEntity entity);
}
