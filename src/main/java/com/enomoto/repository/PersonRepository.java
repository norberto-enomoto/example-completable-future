package com.enomoto.repository;

import java.util.concurrent.CompletableFuture;

import com.enomoto.model.PersonModel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends CrudRepository<PersonModel, Integer> {
    CompletableFuture<PersonModel> findByEmail(String email);

    @Query(value = "SELECT * FROM person_table p where p.email = :email", nativeQuery = true)    
    PersonModel findByEmailSeq(@Param("email") String email);
}