package com.zerohunger.pdsmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerohunger.pdsmanagement.domain.OrderGrant;
import com.zerohunger.pdsmanagement.domain.OrderRequest;
import com.zerohunger.pdsmanagement.domain.State;
import com.zerohunger.pdsmanagement.domain.StateAvailability;
import com.zerohunger.pdsmanagement.repository.OrderGrantRepository;
import com.zerohunger.pdsmanagement.repository.OrderRequestRepository;
import com.zerohunger.pdsmanagement.repository.StateRepository;

import reactor.core.publisher.Mono;

@Service
public class StateManagementService {

	@Autowired
	private StateRepository stateRepo;
	
	@Autowired
	private OrderRequestRepository orderRequestRepo;
	
	@Autowired
	private OrderGrantRepository orderGrantRepo;
	
	public Mono<StateAvailability> getRationAvailability(){
		return null;
	}
	
	public Mono<State> getStateCapacity(String stateId){
		return stateRepo.findById(stateId);
	}
	
	public Mono<OrderRequest> requestforRation(OrderRequest orderRequest){
		return orderRequestRepo.save(orderRequest);
	}

	public Mono<OrderGrant> orderGrant(OrderGrant orderGrant) {
		
		return orderGrantRepo.save(orderGrant);
	}
}
