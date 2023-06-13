package com.green.todoapp.model;

public class TodoEntity {
    private int itodo;
    private String ctnt;
    private String createdAt;
    private int delYn;
    private String pic;

    public int getItodo() {
        return itodo;
    }

    public void setItodo(int itodo) {
        this.itodo = itodo;
    }

    public String getCtnt() {
        return ctnt;
    }

    public void setCtnt(String ctnt) {
        this.ctnt = ctnt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getDelYn() {
        return delYn;
    }

    public void setDelYn(int delYn) {
        this.delYn = delYn;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
