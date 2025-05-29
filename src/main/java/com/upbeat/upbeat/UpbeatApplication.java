package com.upbeat.upbeat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UpbeatApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpbeatApplication.class, args);
	}

}
