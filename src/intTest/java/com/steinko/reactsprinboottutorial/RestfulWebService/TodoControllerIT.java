package com.steinko.reactsprinboottutorial.RestfulWebService;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.springframework.http.HttpStatus.OK;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.context.WebApplicationContext;
import com.steinko.reactsprinboottutorial.DateFactory;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.ClassRule;


import org.testcontainers.containers.PostgreSQLContainer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;


@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = {TodoControllerIT.Initializer.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)


public class TodoControllerIT {
	 private static final Logger logger = LoggerFactory.getLogger(TodoControllerIT.class);
	 
	 
	 @ClassRule
	    public static  PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres:latest")
	        .withDatabaseName("postgres")
	        .withUsername("postgres")
	        .withPassword("root");
	 
	 static class Initializer
     implements ApplicationContextInitializer<ConfigurableApplicationContext> {
       public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
           TestPropertyValues.of(
             "spring.datasource.url=" + postgresqlContainer.getJdbcUrl(),
             "spring.datasource.username=" + postgresqlContainer.getUsername(),
             "spring.datasource.password=" + postgresqlContainer.getPassword()
           ).applyTo(configurableApplicationContext.getEnvironment());
       }
   }
	 
	 @LocalServerPort
	 private  int localServerPort;
	 private TodoTestData testData;
	
	 @Autowired
	  private WebApplicationContext webApplicationContext;
	 
	 @Before
	 public void before() {
		 postgresqlContainer.start();
	    }

	  @After
	  public void after() {
	    	postgresqlContainer.stop();
	    }

	       
	 @Test
	 public void shouldhaveAContinerRunning() {
	    assertTrue(postgresqlContainer.isRunning());
	 }
	   
	 @Ignore
	 @Test
	 public void shoulReturnTodos()  {
	    	  	
		    logger.info("Before Get Todos");
		    testData = new TodoTestData();
			List<TodoDto> dtos = testData.getTodos();
	   	    String createUrl =  "http://localhost:" + localServerPort + "/user/stein/todo";	
	   	 
	   	     for (TodoDto dto : dtos) {
	   	 
	   	        given().
	              contentType("application/json").
	              webAppContextSetup(webApplicationContext).
	              body(dto)
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
	           .body(is(equalTo(dtos)));
	         logger.info("After Get Todos");
	     }
	 
	  

     @Test
     public void shouldCreateTodo()  {
    	 
    	 Date date = DateFactory.generetDate("01-01-2020 12:00:00");	
		 TodoDto dto = new TodoDto(1L,"Stein","Fix kjakk",date,false);
    	 String creatUrl =  "http://localhost:" + localServerPort + "/user/stein/todo";;	
    	 
    	 given().
          contentType("application/json").
          webAppContextSetup(webApplicationContext).
          body(dto)
        .when()
           .post(creatUrl)
        .then()
          .log().ifValidationFails()
          .statusCode(OK.value()); 
     
     }
     
     @Ignore
     @Test
     public void shouldDeleteTodo() {
    	 
    	 Date date = DateFactory.generetDate("01-01-2020 12:00:00");	
		 TodoDto dto = new TodoDto(1L,"Stein","Fix kjakk",date,false);
    	 String creatUrl =  "http://localhost:" + localServerPort + "/user/stein/todo";;	
   	 
    	 given().
          contentType("application/json").
          webAppContextSetup(webApplicationContext).
          body(dto)
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
