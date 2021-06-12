package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.zerohunger.pdsmanagement.domain.OrderGrant;

@Repository
public interface OrderGrantRepository extends ReactiveMongoRepository<OrderGrant, String> {


	
	
}
