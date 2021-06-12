package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.zerohunger.pdsmanagement.domain.RequestStatus;

public interface RequestStatusRepository extends ReactiveMongoRepository<RequestStatus, String> {

}
