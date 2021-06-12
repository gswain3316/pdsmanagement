package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zerohunger.pdsmanagement.domain.OrderRequest;

public interface OrderRequestRepository extends MongoRepository<OrderRequest, String> {

}
