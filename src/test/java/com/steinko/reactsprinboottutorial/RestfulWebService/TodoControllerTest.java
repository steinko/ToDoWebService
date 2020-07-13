package com.steinko.reactsprinboottutorial.RestfulWebService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.http.ContentType.JSON;
import static org.springframework.http.HttpStatus.OK;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyString;
import com.fasterxml.jackson.core.JsonProcessingException;


import java.util.List;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.logging.Level;

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
		  String url = "/users/stein/todos";
		  	
		  TodoTestData testData = new TodoTestData();
		  List<Todo> todos = testData.getTodos();
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
}
