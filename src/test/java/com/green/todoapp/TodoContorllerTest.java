package com.green.todoapp;

import com.green.todoapp.model.TodoInsDto;
import com.green.todoapp.model.TodoVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(TodoContorller.class)
class TodoContorllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodoService service;

    @Test
    @DisplayName("TODO - 등록")
    void postTodo() throws Exception {
        //테스트 패턴 : given - when - then

        //given - 테스트 세팅
        given(service.insTodo(any(TodoInsDto.class))).willReturn(3);

        //when - 실제 실행
        String json = "{ \"ctnt\": \"빨래 개기\" }";
        ResultActions ra = mvc.perform(post("/api/todo")
                                        .content(json)
                                        .contentType(MediaType.APPLICATION_JSON));

        //then - 검증
        ra.andExpect(status().isOk())
        .andExpect(content().string("3"))
        .andDo(print());

        verify(service).insTodo(any());
    }

    @Test
    @DisplayName("TODO - 리스트")
    void getTodo() throws Exception {
        //given - when - then

        //given
        List<TodoVo> mockList = new ArrayList<>();
        //mockList.add(new TodoVo(1, "테스트", "2023", null));
        //.add(new TodoVo(2, "테스트2", "2022", "abc.jpg"));
        given(service.selTodo()).willReturn(mockList);

        //when
        ResultActions ra = mvc.perform(get("/api/todo"));

        //then
        ra.andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(mockList.size())))
        .andExpect(jsonPath("$[*].itodo").exists())
        .andExpect(jsonPath("$[0].itodo").value(1))
        .andExpect(jsonPath("$[0].ctnt").value("테스트"))
        .andDo(print());
        verify(service).selTodo();
    }
}