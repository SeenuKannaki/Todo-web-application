package com.example.Todo.Controller;

import com.example.Todo.Model.Todo;
import com.example.Todo.Services.TodoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoServices todoServices;

    @Autowired
    public TodoController(TodoServices todoServices){
        this.todoServices = todoServices;
    }

    @GetMapping
    public List<Todo> getAll(){
        return todoServices.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id, @RequestBody Todo todo){
        return todoServices.getTodoById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){
        Todo savedTodo = todoServices.createTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todoDetails){
        return ResponseEntity.ok(todoServices.update(todoDetails, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Todo> deleteById(@PathVariable Long id){
        todoServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
