package com.example.todo.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todo")
public class Todo {
    private String id;
    private String text;
    private Boolean deleted;

    public Todo() {

    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Todo(String text){
        this.text = text;
    }
}
