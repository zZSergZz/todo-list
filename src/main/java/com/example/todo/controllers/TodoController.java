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

    @PostMapping(value="/delete")
    public ResponseEntity<?> deleteModel(@RequestBody DeleteRequest deleteRequest) {
        todoService.deleteById(deleteRequest.getId());
        return ResponseEntity.ok(new DeleteResponse("Туду успешно удалена!"));
    }
    
    @PostMapping(value="/todo")
    public ModelAndView todoModel(HttpServletRequest request, HttpServletResponse response, CreateRequest createRequest){

        ModelAndView mav = new  ModelAndView("todo");
        return mav;
    }
    @RequestMapping(value="/todo",method = RequestMethod.GET)
    public ModelAndView todo(HttpServletRequest request, HttpServletResponse response, CreateRequest createRequest)
    {
        ModelAndView mav = new  ModelAndView("todo");

        List<Document> todoList = todoService.findByDeletedIsFalse();
        mav.addObject("todo",todoList);
        return mav;
    }

    @PostMapping(value="/todo-delete")
    public ModelAndView todoDeleteModel(DeleteRequest deleteRequest){

        ModelAndView mav = new  ModelAndView("redirect:http://localhost:8080/api/todo");

        todoService.deleteById(deleteRequest.getId());
        return mav;
    }
    @RequestMapping(value="/todo-delete",method = RequestMethod.GET)
    public ModelAndView todoDelete(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new  ModelAndView("redirect:http://localhost:8080/api/todo");
        return mav;
    }

    @PostMapping(value="/todo-create")
    public ModelAndView todoCreateModel(CreateRequest createRequest){

        ModelAndView mav = new  ModelAndView("redirect:http://localhost:8080/api/todo");
        todoService.createTodo(createRequest.getText());
        return mav;
    }
    @RequestMapping(value="/todo-create",method = RequestMethod.GET)
    public ModelAndView todoCreate(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView mav = new  ModelAndView("redirect:http://localhost:8080/api/todo");
        return mav;
    }

}
