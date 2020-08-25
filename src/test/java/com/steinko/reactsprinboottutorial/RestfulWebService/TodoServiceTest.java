package com.steinko.reactsprinboottutorial.RestfulWebService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TodoServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(TodoServiceTest.class);
	private TodoService service;
	
	public TodoServiceTest ()  { 
		
			service = new TodoService();
		
	}
	
	@Test
	void ShouldExist() {
		assertNotNull (service);
	}
	
	@Disabled
	@Test
	void shouldretunTodos() {
		
		List<TodoDto>result = service.getTodos("stein"); 
		assertThat(result, containsInAnyOrder(
                hasProperty("description", is("Fix mutter")),
                hasProperty("description", is("Fix kjakk"))     
        ));      
	}
	
	@Test
	void shouldDeleteTodo() {
		
		List<TodoDto>result = service.deleteTodo("stein", 1); 
		assertThat(result, containsInAnyOrder(
                hasProperty("description", is("Fix mutter"))    
        ));      
	}
}
