package com.zerohunger.pdsmanagement.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zerohunger.pdsmanagement.domain.RequestStatus;

@Repository
public interface RequestStatusRepository extends MongoRepository<RequestStatus, String> {

	Optional<RequestStatus> findOneByRequestId(final String requestId);
	
}
