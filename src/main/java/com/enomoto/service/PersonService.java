package com.enomoto.service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.enomoto.model.PersonModel;
import com.enomoto.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    
    @Async
    public CompletableFuture<PersonModel> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }
    
    public Iterable<PersonModel> getPersonsConcurrence() throws InterruptedException, ExecutionException {
        // ArrayList<CompletableFuture<PersonModel>> personModelList = new ArrayList<CompletableFuture<PersonModel>>();
        ArrayList<PersonModel> personModelList = new ArrayList<PersonModel>();

        log.info("Getting all Persons Concurrence");
        // Kick of multiple, asynchronous lookups
        long start = System.currentTimeMillis();
        CompletableFuture<PersonModel> person1 = this.findByEmail("mquickenden0@diigo.com");
        CompletableFuture<PersonModel> person2 = this.findByEmail("dpowley1@mozilla.com");
        CompletableFuture<PersonModel> person3 = this.findByEmail("ccottesford2@dmoz.org");
        CompletableFuture<PersonModel> person4 = this.findByEmail("egouinlock3@mozilla.org");
        CompletableFuture<PersonModel> person5 = this.findByEmail("mromain4@alexa.com");
        CompletableFuture<PersonModel> person6 = this.findByEmail("ekiddy5@chicagotribune.com");
        CompletableFuture<PersonModel> person7 = this.findByEmail("rmcintosh6@dot.gov");        

        // Wait until they are all done
        CompletableFuture.allOf(person1, person2, person3, person4, person5, person6, person7).join();

        // Print results, including elapsed time
        log.info("Elapsed time Concurrent Call: " + (System.currentTimeMillis() - start));
        log.info("Person 1 --> " + person1.get());
        log.info("Person 2 --> " + person2.get());
        log.info("Person 3 --> " + person3.get());
        log.info("Person 4 --> " + person4.get());
        log.info("Person 5 --> " + person5.get());
        log.info("Person 6 --> " + person6.get());
        log.info("Person 7 --> " + person7.get());

        personModelList.add(person1.get());
        personModelList.add(person2.get());
        personModelList.add(person3.get());
        personModelList.add(person4.get());
        personModelList.add(person5.get());
        personModelList.add(person6.get());
        personModelList.add(person7.get());

        return personModelList;
    }  


}
