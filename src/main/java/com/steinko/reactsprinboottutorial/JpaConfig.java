package com.steinko.reactsprinboottutorial;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.steinko.reactsprinboottutorial.RestfulWebService")
public class JpaConfig {

}
