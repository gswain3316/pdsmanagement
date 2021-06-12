package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.zerohunger.pdsmanagement.domain.OrderRequest;

@Repository
public interface OrderRequestRepository extends ReactiveMongoRepository<OrderRequest, String> {

}
