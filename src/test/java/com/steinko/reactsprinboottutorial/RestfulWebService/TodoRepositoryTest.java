package com.steinko.reactsprinboottutorial.RestfulWebService;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.steinko.reactsprinboottutorial.RestfulWebServiceApplication;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RestfulWebServiceApplication.class)
public class TodoRepositoryTest {
	
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

}
