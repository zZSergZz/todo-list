package com.example.todo.controllers;

import com.example.todo.payload.request.CreateRequest;
import com.example.todo.payload.request.DeleteRequest;
import com.example.todo.payload.response.DeleteResponse;
import com.example.todo.repository.TodoRepository;
import com.example.todo.services.TodoService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")

public class TodoController {
    @Autowired
    TodoService todoService;
    @Autowired
    TodoRepository todoRepository;

    @PostMapping(value="/todo-list/delete")
    public ResponseEntity<?> deleteModel(@RequestBody DeleteRequest deleteRequest) {
        todoService.deleteById(deleteRequest.get_id());
        return ResponseEntity.ok(new DeleteResponse("Туду успешно удалена!"));
    }
    
    @PostMapping(value="/todo-list/todo")
    public ModelAndView todoModel(HttpServletRequest request, HttpServletResponse response, CreateRequest createRequest){

        ModelAndView mav = new  ModelAndView("todo");
        return mav;
    }
    @RequestMapping(value="/todo-list/todo",method = RequestMethod.GET)
    public ModelAndView todo(HttpServletRequest request, HttpServletResponse response, CreateRequest createRequest)
    {
        ModelAndView mav = new  ModelAndView("todo");

        List<Document> todoList = todoService.findByDeletedIsFalse();
        mav.addObject("todo",todoList);
        return mav;
    }

    @PostMapping(value="/todo-list/todo-create")
    public ModelAndView todoCreateModel(CreateRequest createRequest){

        ModelAndView mav = new  ModelAndView("redirect:http://localhost:8080/api/todo-list/todo");

        todoService.createTodo(createRequest.getText());
        System.out.println(createRequest.getText());
        return mav;
    }
    @RequestMapping(value="/todo-create",method = RequestMethod.GET)
    public ModelAndView todoCreate(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new  ModelAndView("redirect:http://localhost:8080/api/todo-list/todo");
        return mav;
    }

}
