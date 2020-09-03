package com.steinko.reactsprinboottutorial.RestfulWebService;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;


import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.ClassRule;
import org.testcontainers.containers.PostgreSQLContainer;

import com.steinko.reactsprinboottutorial.WebService;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = {TodoRepositoryTest.Initializer.class})
@SpringBootTest(classes = WebService.class)
public class TodoRepositoryTest {
	
	@ClassRule
    public static  PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres:12.4")
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
