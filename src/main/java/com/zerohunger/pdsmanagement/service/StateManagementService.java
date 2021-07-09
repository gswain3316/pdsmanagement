package com.zerohunger.pdsmanagement.service;

import com.zerohunger.pdsmanagement.domain.OrderGrant;
import com.zerohunger.pdsmanagement.domain.OrderRequest;
import com.zerohunger.pdsmanagement.domain.RequestStatus;
import com.zerohunger.pdsmanagement.domain.GovBody;
import com.zerohunger.pdsmanagement.domain.GovBodyRawMaterialAvailability;
import com.zerohunger.pdsmanagement.dto.OrderGrantService;
import com.zerohunger.pdsmanagement.dto.OrderRequestService;

import reactor.core.publisher.Mono;

public interface StateManagementService {

	public Mono<GovBodyRawMaterialAvailability> getRationAvailability(String stateName);
	public Mono<GovBody> getStateCapacity(String stateId);
	public Mono<OrderRequest> requestforRation(OrderRequestService orderRequest);
	public Mono<OrderGrant> grantOrderNote(OrderGrantService orderGrant);
	public Mono<RequestStatus> getOrderStatus(String requestId);
	
}