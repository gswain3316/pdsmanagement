package com.zerohunger.pdsmanagement.repository;

import com.zerohunger.pdsmanagement.domain.GovBody;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends MongoRepository<GovBody, String> {

	GovBody findOneByDistrictName(final String districtName);

	@Query("{'districtName' : ?0, 'isStateIndicator' : ?1}")
    GovBody findGovBodyByStateNameAndIsStateIndicator(final String districtName, final Boolean isStateIndicator);
	
}
