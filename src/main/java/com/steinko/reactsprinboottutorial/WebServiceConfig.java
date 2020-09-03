package com.steinko.reactsprinboottutorial;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class WebServiceConfig {
	
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}
