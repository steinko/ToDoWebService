package com.steinko.reactsprinboottutorial.RestfulWebService;

import org.assertj.core.api.Assertions;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;


	@RunWith(SpringRunner.class)
	@SpringBootTest
	@ContextConfiguration(initializers = {RepositoryIT.Initializer.class})
	public class RepositoryIT  {
	 
	    @ClassRule
	    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres")
	      .withDatabaseName("postgres")
	      .withUsername("postgres")
	      .withPassword("root");
	    
	    @Test
	    public void contextLoads() {
	    }
	 
	    static class Initializer
	      implements ApplicationContextInitializer<ConfigurableApplicationContext> {
	        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
	            TestPropertyValues.of(
	              "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
	              "spring.datasource.username=" + postgreSQLContainer.getUsername(),
	              "spring.datasource.password=" + postgreSQLContainer.getPassword()
	            ).applyTo(configurableApplicationContext.getEnvironment());
	        }
	    }
	}
