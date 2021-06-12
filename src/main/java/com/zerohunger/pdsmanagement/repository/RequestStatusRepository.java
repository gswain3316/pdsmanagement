package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zerohunger.pdsmanagement.domain.RequestStatus;

public interface RequestStatusRepository extends MongoRepository<RequestStatus, String> {

}
