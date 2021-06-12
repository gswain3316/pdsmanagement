package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.zerohunger.pdsmanagement.domain.OrderRequest;

public interface OrderRequestRepository extends ReactiveMongoRepository<OrderRequest, String> {

}
