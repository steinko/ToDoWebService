package com.steinko.reactsprinboottutorial.RestfulWebService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.steinko.reactsprinboottutorial.DateFactory;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {
	
	@InjectMocks
	private TodoService service;
	
	@Mock
	private TodoRepository repository;
	
	
	
	@Test
	void ShouldExist() {
		assertNotNull (service);
	}
	
	@Disabled
	@Test
	void shouldretunTodos() {
		
		Date date =DateFactory.generetDate("01-10-2001");
		List<Todo> todos = new ArrayList<Todo>();
		todos.add(new Todo("Stein", "Fix Mutter",date,false));
		todos.add(new Todo("Stein", "Fix Kjakk",date,false));
		given(repository.findByName("stein"))
		  .willReturn(todos);
	 	
		Iterable<TodoDto>result = service.getTodos("stein"); 
		List<TodoDto> expected = new ArrayList<TodoDto>();
		expected.add(new TodoDto(1L,"Stein", "Fix Mutter", date, true));
		expected.add(new TodoDto(2L,"Stein", "Fix Mutter", date, false));
		Iterable<TodoDto> iterable= expected;
		assertIterableEquals​(iterable,result);     
	}
	
	@Test
	void shouldDeleteTodo() {
		service.deleteTodo("stein", 1L);     
	}
	
	@Test
	void shoulCreateTodo() {
		Date date = DateFactory.generetDate("01-01-2020 12:00:00");	
		TodoDto  todo = new TodoDto(1L,"Stein","Fix Kjakk", date,false);
		service.createTodo(todo);
		
	}
	
}
