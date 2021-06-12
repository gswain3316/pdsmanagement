package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.zerohunger.pdsmanagement.domain.State;

public interface StateRepository extends ReactiveMongoRepository<State, String> {

}
