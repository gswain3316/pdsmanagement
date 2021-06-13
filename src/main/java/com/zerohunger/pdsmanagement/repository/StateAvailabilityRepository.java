package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zerohunger.pdsmanagement.domain.StateAvailability;

@Repository
public interface StateAvailabilityRepository extends MongoRepository<StateAvailability, String> {

	StateAvailability findOneByStateName(final String stateName);
	
}
