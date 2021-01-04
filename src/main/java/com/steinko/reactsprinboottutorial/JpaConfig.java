package com.steinko.reactsprinboottutorial;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import javax.sql.DataSource;
@Configuration

public class JpaConfig {
	@Bean
	public DataSource dataSource() {
		
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://postgres-service:5432/postgres");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("root");
        return dataSourceBuilder.build();
	}
	
}

