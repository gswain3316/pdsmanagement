package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.zerohunger.pdsmanagement.domain.GovBodyRawMaterialAvailability;

@Repository
public interface StateAvailabilityRepository extends MongoRepository<GovBodyRawMaterialAvailability, String> {

	GovBodyRawMaterialAvailability findOneByStateName(final String stateName);

	@Query("{'stateName' : ?0, 'isStateIndicator' : ?1}")
    GovBodyRawMaterialAvailability findGovBodyByStateNameAndIsStateIndicator(final String stateInCamelCase, final Boolean isStateIndicator);
	
}
