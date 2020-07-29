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
	
	
	public TodoService() {
	  
	}

	

	public List<Todo> getTodos(String userName) { 
		
		
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
			
		 
		    List <Todo> todos = new ArrayList<Todo>();
		    todos.add( new Todo(0, "Stein", "Fix mutter", date, false));
		    todos.add( new Todo(1, "Stein", "Fix kajakk", date, false));
		
		logger.info(todos.toString());
		return todos;
	}

	public List<Todo> deleteTodo(String string, int id) {
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
		List<Todo> todos = new ArrayList<Todo>();
	    todos.add( new Todo(0, "Stein", "Fix mutter", date, false));
		return todos;
	}
	

}
