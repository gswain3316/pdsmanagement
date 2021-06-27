package com.zerohunger.pdsmanagement.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerohunger.pdsmanagement.constants.OrderRequestStatus;
import com.zerohunger.pdsmanagement.domain.OrderGrant;
import com.zerohunger.pdsmanagement.domain.OrderRequest;
import com.zerohunger.pdsmanagement.domain.RequestStatus;
import com.zerohunger.pdsmanagement.domain.State;
import com.zerohunger.pdsmanagement.domain.StateAvailability;
import com.zerohunger.pdsmanagement.dto.OrderGrantService;
import com.zerohunger.pdsmanagement.dto.OrderRequestService;
import com.zerohunger.pdsmanagement.exception.OrderRequestSaveError;
import com.zerohunger.pdsmanagement.exception.RequestStatusNotFoundException;
import com.zerohunger.pdsmanagement.repository.OrderGrantRepository;
import com.zerohunger.pdsmanagement.repository.OrderRequestRepository;
import com.zerohunger.pdsmanagement.repository.RequestStatusRepository;
import com.zerohunger.pdsmanagement.repository.StateAvailabilityRepository;
import com.zerohunger.pdsmanagement.repository.StateRepository;
import com.zerohunger.pdsmanagement.service.StateManagementService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class StateManagementServiceImpl implements StateManagementService {

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private OrderRequestRepository orderRequestRepo;

	@Autowired
	private OrderGrantRepository orderGrantRepo;

	@Autowired
	private StateAvailabilityRepository stateAvailabilityRepo;
	
	@Autowired
	private RequestStatusRepository requestStatusRepo;

	@Override
	public Mono<StateAvailability> getRationAvailability(String stateName) {
		log.info("Ration Availability Service Started !");
		return Mono.just(stateAvailabilityRepo.findOneByStateName(stateName));
	}

	@Override
	public Mono<State> getStateCapacity(String stateName) {
		return Mono.just(stateRepo.findOneByStateName(stateName));
	}

	@Override
	public Mono<OrderRequest> requestforRation(OrderRequestService orderRequest) throws OrderRequestSaveError {
		log.info("Request for Ration Service Started !");
		Date date = new Date();
		OrderRequest orderRequestFinal = new OrderRequest(orderRequest.getRequestingStateName(),
				orderRequest.getRawMaterialName(), orderRequest.getQuantity(), orderRequest.getUnits(), true, date,
				date);
		Optional<OrderRequest> dbRes = Optional.ofNullable(orderRequestRepo.save(orderRequestFinal));
		if(dbRes.isPresent()) {
			requestStatusRepo.save(new RequestStatus(dbRes.get().getId(), OrderRequestStatus.PENDING, 0.0));
			return Mono.just(dbRes.get());
		}
		else
			throw new OrderRequestSaveError("Invalid Request is Sent ! Please send Valid Request !");

	}

	@Override
	public Mono<OrderGrant> grantOrderNote(OrderGrantService orderGrant) {
		log.info("Grant Order Service Started !");
		Date date = new Date();
		OrderGrant orderGrantFinal = new OrderGrant(orderGrant.getGrantingStateName(), orderGrant.getRequestId(),
				orderGrant.getQuantityGranted(), date, date);
		Optional<OrderGrant> grantOrderRes = Optional.ofNullable(orderGrantRepo.save(orderGrantFinal));
		if (grantOrderRes.isPresent()) {
			OrderRequest orderChange = orderRequestRepo.findById(orderGrant.getRequestId()).get();
			orderChange.setIsActive(false);
			orderRequestRepo.save(orderChange);
		}
		return Mono.just(grantOrderRes.get());
	}

	@Override
	public Mono<RequestStatus> getOrderStatus(String requestId) throws RequestStatusNotFoundException {
		log.info("Get Order Status Service Started !");
		Optional<RequestStatus> dbRes = requestStatusRepo.findOneByRequestId(requestId);
		if(dbRes.isPresent())
			return Mono.just(dbRes.get());
		else
			throw new RequestStatusNotFoundException(requestId+" is not found in our database records!");
	}
}
