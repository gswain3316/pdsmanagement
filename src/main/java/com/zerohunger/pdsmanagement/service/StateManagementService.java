package com.zerohunger.pdsmanagement.service;

import com.zerohunger.pdsmanagement.domain.OrderGrant;
import com.zerohunger.pdsmanagement.domain.OrderRequest;
import com.zerohunger.pdsmanagement.domain.RequestStatus;
import com.zerohunger.pdsmanagement.domain.State;
import com.zerohunger.pdsmanagement.domain.StateAvailability;
import com.zerohunger.pdsmanagement.dto.OrderGrantService;
import com.zerohunger.pdsmanagement.dto.OrderRequestService;
import com.zerohunger.pdsmanagement.exception.OrderGrantSaveError;
import com.zerohunger.pdsmanagement.exception.OrderRequestSaveError;
import com.zerohunger.pdsmanagement.exception.RequestStatusNotFoundException;

import reactor.core.publisher.Mono;

public interface StateManagementService {

	public Mono<StateAvailability> getRationAvailability(String stateName);
	public Mono<State> getStateCapacity(String stateId);
	public Mono<OrderRequest> requestforRation(OrderRequestService orderRequest) throws OrderRequestSaveError;
	public Mono<OrderGrant> grantOrderNote(OrderGrantService orderGrant) throws OrderGrantSaveError;
	public Mono<RequestStatus> getOrderStatus(String requestId) throws RequestStatusNotFoundException;
	
}