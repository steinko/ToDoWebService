package com.steinko.reactsprinboottutorial.RestfulWebService;

import static org.springframework.test.context.support.TestPropertySourceUtils.addInlinedPropertiesToEnvironment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.springframework.boot.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.springframework.http.HttpStatus.OK;
import java.util.Date;
import java.util.List;

import org.springframework.web.context.WebApplicationContext;
import com.steinko.reactsprinboottutorial.DateFactory;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.ClassRule;


import org.testcontainers.containers.PostgreSQLContainer;
import com.steinko.reactsprinboottutorial.RestfulWebService.TodoTestData;

import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)


public class TodoControllerIT extends AbstractContainerBaseTest{
	 private static final Logger logger = LoggerFactory.getLogger(TodoControllerIT.class);
	 

	 @LocalServerPort
	 private  int localServerPort;
	 private TodoTestData testData;
	
	 @Autowired
	  private WebApplicationContext webApplicationContext;
	 
	 @Container
		public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer();

	       
	 @Test
	 public void shouldhaveAContinerRunning() {
	    assertTrue(postgreSQLContainer.isRunning());
	 }
	   
	
	 @Test
	 public void shoulReturnTodos()  {
	    	  	
		    logger.info("Before Get Todos");
		    testData = new TodoTestData();
			List<Todo> todos = testData.getTodos();
	   	    String createUrl =  "http://localhost:" + localServerPort + "/user/stein/todo";	
	   	 
	   	     for (Todo todo : todos) {
	   	 
	   	        given().
	              contentType("application/json").
	              webAppContextSetup(webApplicationContext).
	              body(todo)
	           .when()
	             .post(createUrl)
	           .then()
	             .statusCode(OK.value());  
	   	        
	         }
	     	     
	        String getUrl =  "http://localhost:" + localServerPort + "/user/stein/todos";	
	   	    given().
	           webAppContextSetup(webApplicationContext)
	        .when()
	          .get(getUrl)
	        .then()
	           .log().ifValidationFails()
	           .statusCode(OK.value())
	           .contentType("application/json")
	           .body(is(equalTo(todos)));
	         logger.info("After Get Todos");
	     }
	 
	  

     @Test
     public void shouldCreateTodo()  {
    	 
    	 Date date = DateFactory.generetDate("01-01-2020 12:00:00");	
		 Todo todo = new Todo("Stein","Fix kjakk",date,false);
    	 String creatUrl =  "http://localhost:" + localServerPort + "/user/stein/todo";;	
    	 
    	 given().
          contentType("application/json").
          webAppContextSetup(webApplicationContext).
          body(todo)
        .when()
           .post(creatUrl)
        .then()
          .log().ifValidationFails()
          .statusCode(OK.value()); 
     
     }
     
  
     @Test 
     public void shouldDeleteTodo() {
    	 
    	 Date date = DateFactory.generetDate("01-01-2020 12:00:00");	
		 Todo todo = new Todo("Stein","Fix kjakk",date,false);
    	 String creatUrl =  "http://localhost:" + localServerPort + "/user/stein/todo";;	
   	 
    	 given().
          contentType("application/json").
          webAppContextSetup(webApplicationContext).
          body(todo)
        .when()
           .post(creatUrl)
        .then()
          .log().ifValidationFails()
          .statusCode(OK.value()); 
    	 
    	 
    	 String deletUrl = "http://localhost:" + localServerPort +"stein/todo/1";
    	 
    	 given().
           contentType("application/json").
           webAppContextSetup(webApplicationContext)
        .when()
          .delete(deletUrl)
       .then()
         .log().ifValidationFails()
         .statusCode(OK.value()); 
    	 
     }


}
