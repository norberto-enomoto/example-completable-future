package com.enomoto;

import java.util.concurrent.CompletableFuture;

import com.enomoto.model.PersonModel;
import com.enomoto.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAsync
@Slf4j
public class PersonSpringBootDataAppApplication implements CommandLineRunner {

	@Autowired
	private PersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(PersonSpringBootDataAppApplication.class, args);
		log.info("The application is using {} mb", (Runtime.getRuntime().totalMemory() / 1024 / 1024));
	}

	@Override
	public void run(String... args) throws Exception {

		// -- Using Sequence call´s		
		long start1 = System.currentTimeMillis();
		PersonModel seqPerson1 = personService.findByEmailSeq("mquickenden0@diigo.com");
		PersonModel seqPerson2 = personService.findByEmailSeq("dpowley1@mozilla.com");
		PersonModel seqPerson3 = personService.findByEmailSeq("ccottesford2@dmoz.org");
		PersonModel seqPerson4 = personService.findByEmailSeq("egouinlock3@mozilla.org");
		PersonModel seqPerson5 = personService.findByEmailSeq("mromain4@alexa.com");
		PersonModel seqPerson6 = personService.findByEmailSeq("ekiddy5@chicagotribune.com");
		PersonModel seqPerson7 = personService.findByEmailSeq("rmcintosh6@dot.gov");

        
		// Print results, including elapsed time
		log.info("Elapsed time Sequence Call: " + (System.currentTimeMillis() - start1));		
  	    log.info("Person 1 --> " + seqPerson1.toString());
		log.info("Person 2 --> " + seqPerson2.toString());
		log.info("Person 3 --> " + seqPerson3.toString());
		log.info("Person 4 --> " + seqPerson4.toString());
		log.info("Person 5 --> " + seqPerson5.toString());
		log.info("Person 6 --> " + seqPerson6.toString());
		log.info("Person 7 --> " + seqPerson7.toString());

		// Kick of multiple, asynchronous lookups
		long start2 = System.currentTimeMillis();
		CompletableFuture<PersonModel> person1 = personService.findByEmail("mquickenden0@diigo.com");
		CompletableFuture<PersonModel> person2 = personService.findByEmail("dpowley1@mozilla.com");
		CompletableFuture<PersonModel> person3 = personService.findByEmail("ccottesford2@dmoz.org");
		CompletableFuture<PersonModel> person4 = personService.findByEmail("egouinlock3@mozilla.org");
		CompletableFuture<PersonModel> person5 = personService.findByEmail("mromain4@alexa.com");
		CompletableFuture<PersonModel> person6 = personService.findByEmail("ekiddy5@chicagotribune.com");
		CompletableFuture<PersonModel> person7 = personService.findByEmail("rmcintosh6@dot.gov");
		
        // Wait until they are all done
		CompletableFuture.allOf(person1, person2).join();

		// Print results, including elapsed time
		log.info("Elapsed time Concurrent Call: " + (System.currentTimeMillis() - start2));
		log.info("Person 1 --> " + person1.get());
		log.info("Person 2 --> " + person2.get());
		log.info("Person 3 --> " + person3.get());
		log.info("Person 4 --> " + person4.get());
		log.info("Person 5 --> " + person5.get());
		log.info("Person 6 --> " + person6.get());
		log.info("Person 7 --> " + person7.get());
	}
}		