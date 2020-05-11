package com.enomoto.service;

import java.util.concurrent.CompletableFuture;

import com.enomoto.model.PersonModel;
import com.enomoto.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public CompletableFuture<PersonModel> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public PersonModel findByEmailSeq(String email) {
        return personRepository.findByEmailSeq(email);
    }


}
