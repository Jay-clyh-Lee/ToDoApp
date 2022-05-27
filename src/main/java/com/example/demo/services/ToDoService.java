package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.ToDo;
import com.example.demo.repositories.ToDoRepository;

// service is the brain of the model. it handles business logic
@Service
public class ToDoService {
	
	@Autowired
	private ToDoRepository repo;
	
	public List<ToDo> getAll() {
		return this.repo.findAll();
	}
	
	public ToDo create(ToDo item) {
		return this.repo.save(item); // repo.save is a built-in function in repository
	}
	
	public ToDo retrieve(Long itemId) {
		return this.repo.findById(itemId).get();
	}
	
	public void delete(Long itemId) {
		this.repo.deleteById(itemId);
	}
	
	public ToDo update(ToDo item) {
		return this.repo.save(item);
	}
}
