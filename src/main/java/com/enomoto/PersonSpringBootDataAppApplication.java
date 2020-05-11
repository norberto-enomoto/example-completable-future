package com.enomoto;

import com.enomoto.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAsync
@Slf4j
public class PersonSpringBootDataAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(PersonSpringBootDataAppApplication.class, args);
		log.info("The application is using {} mb", (Runtime.getRuntime().totalMemory() / 1024 / 1024));
	}	
}		