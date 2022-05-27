package com.example.demo.repositories;

import java.util.*;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ToDo;

// benefit of MVC is also known as separation of concerns

// handles building SQL queries
@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Long>{

	List<ToDo> findAll(); // created manually because built-in findAll() returns weird objects
}
