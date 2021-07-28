package com.zerohunger.pdsmanagement.repository;

import com.zerohunger.pdsmanagement.domain.GovBodyRawMaterialAvailability;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictAvailabilityRepository extends MongoRepository<GovBodyRawMaterialAvailability, String> {

	GovBodyRawMaterialAvailability findOneByDistrictName(final String districtName);

	@Query("{'districtName' : ?0, 'isStateIndicator' : ?1}")
    GovBodyRawMaterialAvailability findGovBodyByStateNameAndIsStateIndicator(final String districtName, final Boolean isStateIndicator);
	
}
