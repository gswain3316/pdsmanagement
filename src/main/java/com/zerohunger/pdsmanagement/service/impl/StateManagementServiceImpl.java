package com.zerohunger.pdsmanagement.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerohunger.pdsmanagement.domain.OrderGrant;
import com.zerohunger.pdsmanagement.domain.OrderRequest;
import com.zerohunger.pdsmanagement.domain.State;
import com.zerohunger.pdsmanagement.domain.StateAvailability;
import com.zerohunger.pdsmanagement.dto.OrderGrantService;
import com.zerohunger.pdsmanagement.dto.OrderRequestService;
import com.zerohunger.pdsmanagement.repository.OrderGrantRepository;
import com.zerohunger.pdsmanagement.repository.OrderRequestRepository;
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
	public Mono<OrderRequest> requestforRation(OrderRequestService orderRequest) {

		Date date = new Date();
		OrderRequest orderRequestFinal = new OrderRequest(orderRequest.getRequestingStateName(),
				orderRequest.getRawMaterialName(), orderRequest.getQuantity(), orderRequest.getUnits(), true, date,
				date);
		return Mono.just(orderRequestRepo.save(orderRequestFinal));

	}

	@Override
	public Mono<OrderGrant> grantOrderNote(OrderGrantService orderGrant) {
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
}
