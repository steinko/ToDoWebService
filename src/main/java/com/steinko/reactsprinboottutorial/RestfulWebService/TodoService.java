package com.steinko.reactsprinboottutorial.RestfulWebService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Service
public class TodoService  {
	
	private static final Logger logger = LoggerFactory.getLogger(TodoService.class);
	private List<Todo> todos;
	
	public TodoService() {
	   createTestData();
	}

	private void createTestData()  {
		SimpleDateFormat df
		   = new SimpleDateFormat("dd-MM-yyyy");
		   Date date;
			try  {		
		          String toParse = "01-01-2020";
		          date = df.parse(toParse);
			 } catch (ParseException ex)
			{
				 logger.info(ex.getMessage());
				 date = new Date();
			}
			
		 
		    todos = new ArrayList<Todo>();
		    todos.add(new Todo(1L, "Stein", "Fix mutter", date, false));
		    todos.add(new Todo(3L, "Stein", "Fix kajakk", date, false));
		    
	}

	public List<Todo> getTodos(String userName) { 
		logger.info(todos.toString());
		return todos;
	}
	

}
