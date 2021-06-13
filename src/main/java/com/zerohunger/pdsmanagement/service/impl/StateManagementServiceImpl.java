package com.zerohunger.pdsmanagement.service.impl;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerohunger.pdsmanagement.constants.UpdateRation;
import com.zerohunger.pdsmanagement.domain.OrderGrant;
import com.zerohunger.pdsmanagement.domain.OrderRequest;
import com.zerohunger.pdsmanagement.domain.RequestStatus;
import com.zerohunger.pdsmanagement.domain.State;
import com.zerohunger.pdsmanagement.domain.StateAvailability;
import com.zerohunger.pdsmanagement.dto.OrderGrantService;
import com.zerohunger.pdsmanagement.dto.OrderRequestService;
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
	public Mono<OrderRequest> requestforRation(OrderRequestService orderRequest) {
		log.info("Request for Ration Service Started !");
		Date date = new Date();
		OrderRequest orderRequestFinal = new OrderRequest(orderRequest.getRequestingStateName(),
				orderRequest.getRawMaterialName(), orderRequest.getQuantity(), orderRequest.getUnits(), true, date,
				date);
		return Mono.just(orderRequestRepo.save(orderRequestFinal));

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
			
			Optional<Object> updatedStateAvailability = Optional.ofNullable(updateRation(orderGrant.getRequestId(),orderGrant.getGrantingStateName(), orderGrant.getQuantityGranted(),UpdateRation.STATE_AVAILABILITY ));
			Optional<Object> updatedOrderRequest = Optional.ofNullable(updateRation(orderGrant.getRequestId(), orderGrant.getGrantingStateName(), orderGrant.getQuantityGranted(),UpdateRation.REQUEST_STATUS));
			
			orderRequestRepo.save(orderChange);
		}
		return Mono.just(grantOrderRes.get());
	}

	@Override
	public Mono<RequestStatus> getOrderStatus(String requestId) {
		log.info("Get Order Status Service Started !");
		return Mono.just(requestStatusRepo.findOneByRequestId(requestId));
	}

	
	public Object updateRation(String requestId, String stateName, Double quantityGranted, UpdateRation objectType) {
		
		Object object = null;
		switch(objectType) {
	      case REQUEST_STATUS:
	    	  RequestStatus requestStatus =requestStatusRepo.findOneByRequestId(requestId);
	    	  if(!Objects.isNull(requestStatus)){
	    		  requestStatus.setQuantityFulfilled(requestStatus.getQuantityFulfilled()-quantityGranted);
	    		  object= requestStatusRepo.save(requestStatus);
	    	  }
	        break;
	      case STATE_AVAILABILITY:
	    	  StateAvailability stateAvailability =stateAvailabilityRepo.findOneByStateName(stateName);
	    	  if(!Objects.isNull(stateAvailability)){
	    		  
	    		  stateAvailability.getAvailability().forEach(a -> {
	    			  if(a.getRawMaterialName()=="Rice") {
	    				  a.setAvailableQuantity(a.getAvailableQuantity()+quantityGranted);
	    			  }
	    		  });
	    		  object= stateAvailabilityRepo.save(stateAvailability);
	    	  }
	         break;
	    }
		return object;
	}
}
