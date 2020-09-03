package com.steinko.reactsprinboottutorial.RestfulWebService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;


public class DataFactoryTest {
	
	@Test
	public void shouldExist() {
		DateFactory factory = new DateFactory();
		assertNotNull(factory);			
	}
	
	
	@Test
	public void shoulConvertStringToDate() {
		DateFactory factory = new DateFactory();
		Date date = factory.generetDate("01-01-2020 12:00:00");	
		assertNotNull(date);		
	}

}
