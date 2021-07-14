package com.zerohunger.pdsmanagement.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zerohunger.pdsmanagement.constants.OrderRequestStatus;
import com.zerohunger.pdsmanagement.constants.StateList;
import com.zerohunger.pdsmanagement.domain.OrderGrant;
import com.zerohunger.pdsmanagement.domain.OrderRequest;
import com.zerohunger.pdsmanagement.domain.RequestStatus;
import com.zerohunger.pdsmanagement.domain.GovBody;
import com.zerohunger.pdsmanagement.domain.GovBodyRawMaterialAvailability;
import com.zerohunger.pdsmanagement.dto.OrderGrantService;
import com.zerohunger.pdsmanagement.dto.OrderRequestService;
import com.zerohunger.pdsmanagement.exception.EntityNotFoundException;
import com.zerohunger.pdsmanagement.exception.OrderGrantSaveError;
import com.zerohunger.pdsmanagement.exception.OrderRequestSaveError;
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

	private static String StateInCamelCase = "";

	private static Boolean isStateIndicator =false;

	@Override
	public Mono<GovBodyRawMaterialAvailability> getRationAvailability(String stateName) throws IncorrectResultSizeDataAccessException{
		log.info("Ration Availability Service Started !");
		StateInCamelCase = StringUtils.capitalize(StateList.getStateList(StringUtils.capitalize(stateName)).getName());
		isStateIndicator = true;
		Optional<GovBodyRawMaterialAvailability> availableState = Optional.ofNullable(stateAvailabilityRepo.findGovBodyByStateNameAndIsStateIndicator(StateInCamelCase, isStateIndicator));
		if(availableState.isPresent()){
			log.info("Ration Availability Service Completed !");
			return Mono.just(availableState.get());
		}else{
			log.info("Ration Availability Service Error ! - State Not Found");
			return Mono.error(new EntityNotFoundException("State Not Found "+stateName));
		}
	}

	@Override
	public Mono<GovBody> getStateCapacity(String stateName) throws IncorrectResultSizeDataAccessException{
		log.info("State Capacity Service Started !");
		StateInCamelCase = StringUtils.capitalize(StateList.getStateList(StringUtils.capitalize(stateName)).getName());
		isStateIndicator = true;
		Optional<GovBody> stateCapacity = Optional.ofNullable(stateRepo.findGovBodyByStateNameAndIsStateIndicator(StateInCamelCase, isStateIndicator));
		if(stateCapacity.isPresent()){
			log.info("State Capacity Service Completed !");
			return Mono.just(stateCapacity.get());
		}else{
			log.info("State Capacity Service Error ! - State Not Found");
			return Mono.error(new EntityNotFoundException("State Not Found "+stateName));
		}
	}

	@Override
	public Mono<OrderRequest> requestforRation(OrderRequestService orderRequest) throws IncorrectResultSizeDataAccessException{
		log.info("Request for Ration Service Started !");
		StateInCamelCase = StringUtils.capitalize(StateList.getStateList(StringUtils.capitalize(orderRequest.getRequestingStateName())).getName());
		isStateIndicator = true;
		if (StateInCamelCase.equals("Unknown")|| StateInCamelCase.equals("")){
			log.info("Request for Ration Service Error ! - State Not Found");
			return Mono.error(new EntityNotFoundException("State Not Found "+orderRequest.getRequestingStateName()));
		}
		Date date = new Date();
		OrderRequest orderRequestFinal = new OrderRequest(StateInCamelCase,
				orderRequest.getRawMaterialName(), orderRequest.getQuantity(), orderRequest.getUnits(), true, date,
				date, isStateIndicator);
		Optional<OrderRequest> dbRes = Optional.ofNullable(orderRequestRepo.save(orderRequestFinal));
		if(dbRes.isPresent()) {
			requestStatusRepo.save(new RequestStatus(dbRes.get().getId(), OrderRequestStatus.PENDING, 0.0, dbRes.get().getQuantity()));
			log.info("Request for REQUEST STATUS Completed ! Quantity Remaining: " + dbRes.get().getQuantity());
			return Mono.just(dbRes.get());
		}
		else
			return Mono.error(new OrderRequestSaveError("Request for Ration Service Error ! - Order Request Save Failed"));

	}

	@Override
	public Mono<OrderGrant> grantOrderNote(OrderGrantService orderGrant) throws IncorrectResultSizeDataAccessException {
		log.info("Grant Order Service Started !");
		StateInCamelCase = StringUtils.capitalize(StateList.getStateList(StringUtils.capitalize(orderGrant.getGrantingStateName())).getName());
		isStateIndicator = true;
		if (StateInCamelCase.equals("Unknown")|| StateInCamelCase.equals("")){
			log.info("Request for Ration Service Error ! - State Not Found");
			return Mono.error(new EntityNotFoundException("State Not Found "+orderGrant.getGrantingStateName()));
		}
		Date date = new Date();
		OrderGrant orderGrantFinal = new OrderGrant(orderGrant.getGrantingStateName(), orderGrant.getRequestId(),
				orderGrant.getQuantityGranted(), date, date, isStateIndicator);
		Optional<OrderGrant> grantOrderRes = Optional.ofNullable(orderGrantRepo.save(orderGrantFinal));
		if (grantOrderRes.isPresent()) {
			updateOrderRequestAndRequestStatusOnGrantOrderNote(grantOrderRes.get());
			return Mono.just(grantOrderRes.get());
		}
		else
			return Mono.error(new OrderGrantSaveError("Grant Order Service Error ! - Order Grant Save Failed"));
		
	}

	@Override
	public Mono<RequestStatus> getOrderStatus(String requestId) throws IncorrectResultSizeDataAccessException {
		log.info("Get Order Status Service Started !");
		Optional<RequestStatus> dbRes = requestStatusRepo.findOneByRequestId(requestId);
		if (dbRes.isPresent()) {
			log.info("Get Order Status Service Completed !");
			return Mono.just(dbRes.get());
		}
		else{
			log.info("Get Order Status Service Error ! - Request Not Found");
			return Mono.error(new EntityNotFoundException("Request Not Found "+requestId));
		}
	}
	
	private void updateOrderRequestAndRequestStatusOnGrantOrderNote(OrderGrant orderGrant) {
		OrderRequest orderRequest = orderRequestRepo.findById(orderGrant.getRequestId()).get();
		RequestStatus requestStatus = requestStatusRepo.findOneByRequestId(orderGrant.getRequestId()).get();
		if(orderRequest.getQuantity().equals(orderGrant.getQuantity())) {
			orderRequest.setIsActive(false);
			requestStatus.setStatus(OrderRequestStatus.FULFILLED);
		}
		else 
			requestStatus.setStatus(OrderRequestStatus.PARTIALY_FULFILLED);
		Double remainingQuantity = orderRequest.getQuantity() - orderGrant.getQuantity();
		orderRequest.setQuantity(remainingQuantity);
		requestStatus.setQuantityFulfilled(orderGrant.getQuantity());
		requestStatus.setQuantityRemaining(remainingQuantity);
		orderRequestRepo.save(orderRequest);
		requestStatusRepo.save(requestStatus);	
		
	}
}
