package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zerohunger.pdsmanagement.domain.GovBody;

@Repository
public interface StateRepository extends MongoRepository<GovBody, String> {

	GovBody findOneByStateName(final String stateName);
	
}
