package com.learning.JsonWT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@EnableMongoAuditing
@SpringBootApplication(scanBasePackages = "com.learning.JsonWT")
public class JsonWtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonWtApplication.class, args);
	}

}
