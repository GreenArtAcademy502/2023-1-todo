package com.green.todoapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TodoVo {
    private int itodo;
    private String ctnt;
    private String createdAt;
    private String pic;
    private int finishYn;
    private String finishedAt;
}
