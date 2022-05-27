package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.ToDo;
import com.example.demo.services.ToDoService;

// / /// / /      ALT+SHIFT+Y word wrap

// Controller vs RestController
// Controller: returns template along with data
// RestController: returns literal return values

@Controller
public class ToDoController {
	
	@Autowired
	private ToDoService service;
	
	// GetMapping works the same as RequestMapping
	@GetMapping("/")
	public String home() {
		return "home.jsp";
	}

	// Add
	@GetMapping("/todo/add")
	public String addGetForm(@ModelAttribute("todo") ToDo todo) {
		return "addToDo.jsp";
	}
	
	// Add
	@GetMapping("/todo/add")
	public String addGetFormSession() {
		return "addToDo.jsp";
	}

	@PostMapping("/todo/add")
	public String add(
			@ModelAttribute("todo") ToDo todo,  // @ModelAttribute takes form data and convert them to an object matching the model attributes. That is the reason we need the empty constructor
//			@RequestParam(value="text") String text,  // we don't need this anymore. Why? ModelAttribute handles all field
			RedirectAttributes redirectAttributes
			) {
		
		this.service.create(todo);
		
		redirectAttributes.addFlashAttribute("message", "Your ToDo has been added.");
		
		return "redirect:/todo/my";
	}
	@PostMapping("/todo/add")
	public String addSession(
			@RequestParam(value="text") String text,
			HttpSession session,
			RedirectAttributes redirectAttributes
			) {
		
		// ternary statement(3 parts)
		// test statement                                                 //if true do this      // else do this
		ArrayList<ToDo> todos = (session.getAttribute("todos") == null) ? new ArrayList<ToDo>() : (ArrayList<ToDo>) session.getAttribute("todos");
		// if there's nothing (no arrays) in the session --> creates session, else --> pull the info
		todos.add(new ToDo( Long.valueOf(todos.size()+1), text));  // this pulls info from constructor and create object which is less easy than using @ModelAttribute
		session.setAttribute("todos", todos);
		
		// "message" is a key
		redirectAttributes.addFlashAttribute("message", "Your ToDo has been added.");
		
		return "redirect:/todo/my";
	}
	
	// View all
	@GetMapping("/todo/my")
	public String todo(Model model) {
		
		model.addAttribute("items", this.service.getAll());
		return "todos.jsp";
	}
	
	@GetMapping("/todo/my")
	public String todoSession(Model model, HttpSession session) {
		
		@SuppressWarnings("unchecked")
		ArrayList<ToDo> todos = (session.getAttribute("todos") == null) ? new ArrayList<ToDo>() : (ArrayList<ToDo>) session.getAttribute("todos");
		// in order to pass the information here to the template, we use Model model
		
		model.addAttribute("items", todos);
		return "todos.jsp";
	}
	
	// View by id
	@GetMapping("/todo/{id}")
	public String viewItem(
			@PathVariable Long id,
			Model model
			) {
		
		model.addAttribute("todo", this.service.retrieve(id));
		
		return "viewTodo.jsp";
	}
	
	@GetMapping("/todo/{id}")
	public String viewItemSession(
			@PathVariable Long id,
			Model model,
			HttpSession session
			) {
		
		@SuppressWarnings("unchecked")
		ArrayList<ToDo> todos = (session.getAttribute("todos") == null) ? new ArrayList<ToDo>() : (ArrayList<ToDo>) session.getAttribute("todos");
		// in order to pass the information here to the template, we use Model model
		
		// get the one matching with id
		ToDo toDo = (ToDo) todos.stream().filter(todo -> todo.getId() == id).collect(Collectors.toList()).get(0);
		
		model.addAttribute("todo", toDo);
		
		return "viewTodo.jsp";
	}
	
	// Delete
	@GetMapping("/todo/delete/{id")
	public String delete(
			@PathVariable Long id,
			RedirectAttributes redirectAttributes
			) {
		this.service.delete(id);
		
		redirectAttributes.addFlashAttribute("message", "Your ToDo has been deleted.");
		
		return "redirect:/todo/my";
	}
}
