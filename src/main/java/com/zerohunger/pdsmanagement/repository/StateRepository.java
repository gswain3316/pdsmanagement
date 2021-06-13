package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zerohunger.pdsmanagement.domain.State;

@Repository
public interface StateRepository extends MongoRepository<State, String> {

	State findOneByStateName(final String stateName);
	
}
