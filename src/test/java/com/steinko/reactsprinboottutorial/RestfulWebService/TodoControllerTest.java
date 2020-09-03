package com.steinko.reactsprinboottutorial.RestfulWebService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.http.ContentType.JSON;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyString;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.steinko.reactsprinboottutorial.DateFactory;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@ExtendWith(MockitoExtension.class)
public class TodoControllerTest {
	  private static final Logger logger = LoggerFactory.getLogger(TodoControllerTest.class);
	 
	  @Mock  
	  private TodoService service;
	  
	  @InjectMocks
	  private TodoController controller;
		   
		   
	  @Test
	  void shouldExist() {
		  assertNotNull(controller);
	  }
	  
	  
	  @Test
	    public void shouldReciveHelloWorld() throws Exception,JsonProcessingException,ParseException {
		  String url = "/user/stein/todos";
		  	
		  TodoTestData testData = new TodoTestData();
		  List<TodoDto> todos = testData.getTodos();
		  String todosjson = testData.getTodosJson();
		   
		   when(service.getTodos(anyString())).thenReturn(todos);
		   
		   given().
		     standaloneSetup(controller)
          .when()
            .get(url)
          .then()
          .log().ifValidationFails()
          .statusCode(OK.value())
          .contentType(JSON)
          .body(is(equalTo(todosjson)));
        } 
	  
	  @Test
	 
	    public void shouldDeleteTodo() throws Exception,JsonProcessingException,ParseException {
		  String url = "/user/Stein/todo/1";
		  Date date = DateFactory.generetDate("01-01-2020 12:00:00");	
		  	
		  
		  List<TodoDto> todos = new ArrayList<TodoDto>();
		  todos.add(new TodoDto(0L, "Stein", "Fix mutter", date, false));
		  ObjectMapper objectMapper = new ObjectMapper();
		   
		  String todosjson = "";
		   try {
		   
	         todosjson =   objectMapper.writeValueAsString(todos);
		   } catch (JsonProcessingException ex)
		   {
			   logger.info(ex.getMessage());
		   }
		 
		   
		   given().
		     standaloneSetup(controller)
          .when()
             .delete(url)
           .then()
             .log().ifValidationFails()
             .statusCode(OK.value());
 
      } 
	  
	  @Test 
	  void shouldCreateTodo() throws JsonProcessingException  {
		  Date date = DateFactory.generetDate("01-01-2020 12:00:00");	
			
		  TodoDto todo = new TodoDto(1L,"Stein","Fix kjakk",date,false);
		  ObjectMapper objectMapper = new ObjectMapper();
	        String todosjson =   objectMapper.writeValueAsString(todo);     
	        
		  given().
		    standaloneSetup(controller).
		    contentType(JSON).
		    body(todosjson).
	      when().
	         post("/user/Stein/todo").
	      then().
	         log().ifValidationFails().
	         statusCode(OK.value());
	   }
}
