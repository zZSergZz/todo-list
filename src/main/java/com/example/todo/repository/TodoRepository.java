package com.example.todo.repository;

import com.example.todo.models.Todo;
import org.bson.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodoRepository extends MongoRepository<Todo, String> {
    List<Document> findByDeletedIsFalse();
    void deleteById(String id);
}
