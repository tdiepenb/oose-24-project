package com.sse.ooseproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
public class OoseProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(OoseProjectApplication.class, args);
	}

	@Bean
	public DataSource dataSource() {
        return new DriverManagerDataSource();
	}
}
