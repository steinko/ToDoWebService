package com.steinko.reactsprinboottutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import co.elastic.apm.attach.ElasticApmAttacher;

@SpringBootApplication
public class WebService {

	public static void main(String[] args) {
		ElasticApmAttacher.attach();
		SpringApplication.run(WebService.class, args);
	}

}
