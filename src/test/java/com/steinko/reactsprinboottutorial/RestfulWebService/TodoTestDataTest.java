package com.steinko.reactsprinboottutorial.RestfulWebService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.CoreMatchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TodoTestDataTest {
	
	private static final Logger logger = LoggerFactory.getLogger(TodoTestDataTest.class);
	private TodoTestData testData;
	private List<Todo> todos;
	private String todosjson;
	
	TodoTestDataTest() throws ParseException, JsonProcessingException  {
	   testData = new TodoTestData();
	   SimpleDateFormat df  = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	   Date date;
		  try  {		
	          String toParse = "01-01-2020 02:30:00";
	          date = df.parse(toParse);
		  } catch (ParseException ex)
		  { 
			 logger.info(ex.getMessage());
			 throw ex;
		  }
		
	   todos = new ArrayList<Todo>();
	   todos.add(new Todo(1, "Stein", "Fix mutter", date, false));
	   todos.add(new Todo(2, "Oddmund", "Fix kajak", date, false));
			  
	   ObjectMapper objectMapper = new ObjectMapper();
	     try {
			   
	           todosjson =   objectMapper.writeValueAsString(todos);
		   } catch (JsonProcessingException ex)
		   {
			   logger.info(ex.getMessage());
			   throw ex;
		   }
    }
	
	@Test
	void shouldExists() {
		assertNotNull(testData);
	}
	
	@Disabled
	@Test
	void shouldReturnTodos() {
		
		List<Todo>result = testData.getTodos(); 
		assertThat(result, containsInAnyOrder(
                hasProperty("username", is("Stein"))
        ));      
		
	}
	@Disabled
	@Test
	void shouldReturnTodosJson() {
		
		String result = testData.getTodosJson(); 
		assertEquals(result,todosjson); 		
	}

}
