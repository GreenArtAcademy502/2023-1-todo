package com.green.todoapp;

import com.green.todoapp.model.TodoEntity;
import com.green.todoapp.model.TodoVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TodoMapperTest {

    @Autowired
    private TodoMapper mapper;

    @Test
    void insTodo() {
        //given
        TodoEntity entity = new TodoEntity();
        entity.setCtnt("테스트");

        int result = mapper.insTodo(entity);

        assertEquals(2, entity.getItodo());
        assertEquals(1, result);
    }

    @Test
    void selTodo() {
        List<TodoVo> list = mapper.selTodo();

        assertEquals(1, list.size());
        TodoVo vo = list.get(0);
        assertEquals(1, vo.getItodo());
        assertEquals("asdfasdf", vo.getCtnt());
        assertEquals("2023-06-14 10:20:41", vo.getCreatedAt());
    }

    @Test
    void updFinish() {
        TodoEntity entity = new TodoEntity();
        entity.setItodo(1);

        int result = mapper.updFinish(entity);

        assertEquals(1, result);
    }

    @Test
    @DisplayName("TodoMapper - Todo 삭제")
    void delTodo() {
        int expectedResult = 1;
        TodoEntity entity = new TodoEntity();
        entity.setItodo(1);

        int actualResult = mapper.delTodo(entity);
        assertEquals(expectedResult, actualResult);
    }
}