package com.steinko.reactsprinboottutorial.RestfulWebService;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
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

import org.testcontainers.containers.PostgreSQLContainer;
import com.steinko.reactsprinboottutorial.RestfulWebService.TodoTestData;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TodoRepositoryTest  extends AbstractContainerBaseTest {
	
		
	@Autowired
	private TodoRepository repository;
	
	
	@Test 
	public void shouldNotBeNull () {
		assertNotNull(repository);
	}
	
	

	@Test
	public void shouldSave() {
		repository.save(new Todo("Stein"));
		assertNotNull(repository.findAll());
	}
	

	@Test
	public void shouldDelete() {
		repository.save(new Todo("Stein"));
		Iterable<Todo> todos = repository.findAll();
		Iterator<Todo> iterator = todos.iterator();
		Todo todo = iterator.next();
		Long id = todo.getId();
		repository.deleteById(id);
		List<Todo> list = new ArrayList<Todo>();
		Iterable<Todo> expected = list;
		assertEquals(repository.findAll(), expected);
	}

}
