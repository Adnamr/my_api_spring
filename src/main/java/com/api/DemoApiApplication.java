package com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages  = {"com.api.*"})
@EntityScan("com.api.entities")
public class DemoApiApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(DemoApiApplication.class, args);
	}

}
