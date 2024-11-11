package com.example.Todo.Services;

import com.example.Todo.Model.Todo;
import com.example.Todo.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServices {


    private final TodoRepository todoRepository;

    @Autowired
    public TodoServices(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAll(){
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Long id){
        return todoRepository.findById(id);
    }

    public Todo createTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public Todo update(Todo todoDetails, Long id){
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found"));

        todo.setTitle(todoDetails.getTitle());
        todo.setDescription((todoDetails.getDescription()));
        todo.setCompleted(todoDetails.isCompleted());

        return todoRepository.save(todo);
    }

    public void deleteById(Long id){
        todoRepository.deleteTodo(id);
    }
}
