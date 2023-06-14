package com.green.todoapp;

import com.green.todoapp.model.TodoEntity;
import com.green.todoapp.model.TodoFinishDto;
import com.green.todoapp.model.TodoInsDto;
import com.green.todoapp.model.TodoVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({ TodoService.class })
class TodoServiceTest {

    @MockBean
    private TodoMapper mapper;

    @Autowired
    private TodoService service;

    @Test
    @DisplayName("TodoService - Todo 등록")
    void insTodo() {
        when(mapper.insTodo(any(TodoEntity.class))).thenReturn(1);

        TodoInsDto dto = new TodoInsDto();
        dto.setCtnt("내용 입력");
        int result = service.insTodo(dto);

        assertEquals(0, result);

        verify(mapper).insTodo(any(TodoEntity.class));
    }

    @Test
    @DisplayName("TodoService - Todo 리스트 가져오기")
    void selTodo() {
        //given
        List<TodoVo> mockList = new ArrayList<>();
        mockList.add(new TodoVo(1, "내용", "2023", null, 1, "2023-05-11"));
        mockList.add(new TodoVo(2, "내용2", "2022", "abc.jpg", 0, null));

        //when
        when(mapper.selTodo()).thenReturn(mockList);
        List<TodoVo> actualList = service.selTodo();

        //then
        assertEquals(mockList, actualList);

        verify(mapper).selTodo();
    }

    @Test
    @DisplayName("TodoService - Todo 완료처리 토글")
    void updFinish() {
        //given
        TodoFinishDto dto = new TodoFinishDto();
        dto.setItodo(1);
        TodoEntity entity = new TodoEntity();
        entity.setFinishYn(1);
        entity.setItodo(dto.getItodo());

        //when
        when(mapper.updFinish(entity)).thenReturn(1);
        int result = service.updFinish(dto);

        //then
        assertEquals(0, result);

        verify(mapper).updFinish(any());
    }

    @Test
    @DisplayName("TodoService - Todo 삭제")
    void delTodo() {
        int expectedResult = 1;
        when(mapper.delTodo(any(TodoEntity.class))).thenReturn(expectedResult);

        int result = service.delTodo(anyInt());
        assertEquals(expectedResult, result);

        verify(mapper).delTodo(any(TodoEntity.class));
    }
}