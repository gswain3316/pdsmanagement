package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zerohunger.pdsmanagement.domain.State;

public interface StateRepository extends MongoRepository<State, String> {

}
