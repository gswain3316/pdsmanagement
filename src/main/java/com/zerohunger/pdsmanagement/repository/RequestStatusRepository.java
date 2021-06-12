package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.zerohunger.pdsmanagement.domain.RequestStatus;

@Repository
public interface RequestStatusRepository extends ReactiveMongoRepository<RequestStatus, String> {

}
