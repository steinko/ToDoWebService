package com.steinko.reactsprinboottutorial.RestfulWebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.http.HttpStatus.OK;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
public class TodoControllerAPITest {
	
	 @Autowired
	  private WebApplicationContext webApplicationContext;
	
	public void delete(String deletUrl) {
		given().
           contentType("application/json").
           webAppContextSetup(webApplicationContext)
        .when()
          .delete(deletUrl)
       .then()
         .log().ifValidationFails()
         .statusCode(OK.value());
	}
	
	public Long createTodo(Todo todo, String creatUrl) {
		MockMvcResponse reponse =
    	 given().
          contentType("application/json").
          webAppContextSetup(webApplicationContext).
          body(todo)
        .when()
           .post(creatUrl)
        .then()
          .log().ifValidationFails()
          .contentType("application/json")
          .statusCode(OK.value())
          .extract().
	        response();
    	 Long responsId = reponse.body().as(Long.class);
    	 return  responsId;
	}

}
