package com.example.todo.services;

import com.example.todo.models.Todo;
import com.example.todo.repository.TodoRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Document> findByDeletedIsFalse() {
        return todoRepository.findByDeletedIsFalse();
    }
    @Transactional
    public void deleteById(String id) {
        todoRepository.deleteById(id);
    }

    public Todo createTodo(String text) {
        Todo todo = new Todo();
        todo.setDeleted(false);
        todo.setText(text);
        todo = todoRepository.save(todo);
        return todo;
    }
}
