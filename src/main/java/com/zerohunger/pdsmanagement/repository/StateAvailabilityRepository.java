package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.zerohunger.pdsmanagement.domain.StateAvailability;

@Repository
public interface StateAvailabilityRepository extends ReactiveMongoRepository<StateAvailability, String> {

}
