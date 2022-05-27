package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="todos")

public class ToDo {
	
	// essential for all tables
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String text;
	
	// getters and setters help control the flow of information between private attributes and the "outside world."
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	// contrusctor
	public ToDo() {} // minimum requirement
	
	// this constructor allows us to generate a ToDo objective by passing id and text
	public ToDo(Long id, String text) {
		this.id = id;
		this.text = text;
	}
	
	
	
}
