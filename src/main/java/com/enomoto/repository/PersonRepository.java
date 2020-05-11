package com.enomoto.repository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import com.enomoto.model.PersonModel;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public interface PersonRepository extends CrudRepository<PersonModel, Integer> {    
    CompletableFuture<PersonModel> findByEmail(String email);

    @Bean
    public default Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("PersonService-");
        executor.initialize();
        return executor;
    }
}

