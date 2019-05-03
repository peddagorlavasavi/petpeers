package com.hcl.petpeersapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PetpeersappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetpeersappApplication.class, args);
	}

}
