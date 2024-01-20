package com.serezka.lab5;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab5 implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(Lab5.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

	}
}
