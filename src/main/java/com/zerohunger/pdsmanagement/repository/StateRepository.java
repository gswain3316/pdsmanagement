package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.zerohunger.pdsmanagement.domain.GovBody;

@Repository
public interface StateRepository extends MongoRepository<GovBody, String> {

	@Query("{'stateName' : ?0, 'districtName' : ?1}")
	GovBody findGovBodyByStateNameAndDistrictName(final String stateName, final String districtName);

	@Query("{'stateName' : ?0, 'isStateIndicator' : ?1}")
	GovBody findGovBodyByStateNameAndIsStateIndicator(final String stateInCamelCase, final Boolean isStateIndicator);
	
}
