package com.steinko.reactsprinboottutorial.RestfulWebService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
	
	@Test
	void shouldExcists() { 
		Todo todo = new Todo("Steing Korsveien");
		assertNotNull(todo);
	}
	
	@Test 
	void shouldGetAUserName() { 
		String name  = "Stein Korsveien";
		Todo todo = new Todo(name);
		assertEquals(todo.getName(),name);
	}

}
