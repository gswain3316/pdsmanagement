package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.zerohunger.pdsmanagement.domain.State;

@Repository
public interface StateRepository extends ReactiveMongoRepository<State, String> {

}
