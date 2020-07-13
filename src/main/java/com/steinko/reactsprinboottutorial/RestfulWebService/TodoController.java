package com.steinko.reactsprinboottutorial.RestfulWebService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.ParseException;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/users")
public class TodoController {
	
	
	private final TodoService service;
	
	public TodoController(TodoService service)  {
		this.service = service;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(TodoController.class);
	
	@GetMapping(value= "{user_name}/todos")
	public List<Todo> getPerson(@PathVariable("user_name") String userName) {
		List<Todo> result;
				
			result = service.getTodos(userName);
		
		logger.info(result.toString());
		return result;
	}
	
}
