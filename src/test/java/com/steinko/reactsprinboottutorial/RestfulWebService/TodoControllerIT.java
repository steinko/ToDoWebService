package com.steinko.reactsprinboottutorial.RestfulWebService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.http.ContentType.JSON;
import static org.springframework.http.HttpStatus.OK;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.ParseException;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerIT {
	 private static final Logger logger = LoggerFactory.getLogger(TodoControllerIT.class);
	 @LocalServerPort
	 private  int localServerPort;
	 private String url;
	 private TodoTestData testData;
	
	 @Autowired
	  private WebApplicationContext webApplicationContext;
	 
     @BeforeEach
     void setUp()  {
	    url =  "http://localhost:" + localServerPort + "/users/stein/todos";;	
     }
     @Disabled
     @Test
     void shoulReturnPerson()  {
    	   
		 logger.info("Before Get Todos");
		 logger.info(url);
		 testData = new TodoTestData();
		 String dataJson = testData.getTodosJson();
		 logger.info(dataJson);
		 
         given().
          contentType("application/json").
           webAppContextSetup(webApplicationContext)
         .when()
            .get(url)
         .then()
           .log().ifValidationFails()
           .statusCode(OK.value())
           .contentType(JSON)
           .body(is(equalTo(dataJson)));
         logger.info("After Get Todos");
   }


}
