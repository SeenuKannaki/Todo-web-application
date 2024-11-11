package com.example.Todo.Repository;

import com.example.Todo.Model.Todo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Todo save(Todo todo){
        if(todo.getId() == null){
            entityManager.persist(todo);
            return todo;
        }
        return entityManager.merge(todo);
    }

    public List<Todo> findAll(){
        return entityManager.createQuery("SELECT t FROM Todo t", Todo.class).getResultList();
    }

    public Optional<Todo> findById(Long id){
        Todo todo = entityManager.find(Todo.class, id);
        return Optional.ofNullable(todo);
    }

    @Transactional
    public Todo update(Todo todo){
        return entityManager.merge(todo);
    }

    @Transactional
    public void deleteTodo(Long id){
        Todo todo = entityManager.find(Todo.class, id);
        if(todo != null){
            entityManager.remove(todo);
        }
    }
}
