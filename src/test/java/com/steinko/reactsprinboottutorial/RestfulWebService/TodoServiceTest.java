package com.steinko.reactsprinboottutorial.RestfulWebService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import com.fasterxml.jackson.core.JsonProcessingException;

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
	
	@Test
	void shouldretunTodos() {
		
		List<Todo>result = service.getTodos("stein"); 
		assertThat(result, containsInAnyOrder(
                hasProperty("username", is("Stein")),
                hasProperty("username", is("Oddmund"))
        ));      
	}
}
