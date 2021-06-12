package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.zerohunger.pdsmanagement.domain.StateAvailability;

public interface StateAvailabilityRepository extends ReactiveMongoRepository<StateAvailability, String> {

}
