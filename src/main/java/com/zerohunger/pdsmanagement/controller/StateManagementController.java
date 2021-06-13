package com.zerohunger.pdsmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zerohunger.pdsmanagement.domain.OrderGrant;
import com.zerohunger.pdsmanagement.domain.OrderRequest;
import com.zerohunger.pdsmanagement.domain.State;
import com.zerohunger.pdsmanagement.domain.StateAvailability;
import com.zerohunger.pdsmanagement.dto.OrderGrantService;
import com.zerohunger.pdsmanagement.dto.OrderRequestService;
import com.zerohunger.pdsmanagement.service.StateManagementService;

import reactor.core.publisher.Mono;

@RestController("/pdsapp/state")
public class StateManagementController {

	@Autowired
	private StateManagementService stateManagementService;

	@GetMapping("/ration-availability")
	public Mono<ResponseEntity<StateAvailability>> getRationAvailability(@PathVariable String stateName) {
		if (stateName != null) {
			return stateManagementService.getRationAvailability(stateName).map(state -> ResponseEntity.ok(state))
					.defaultIfEmpty(ResponseEntity.notFound().build());
		} else
			return Mono.just(new ResponseEntity<>(new StateAvailability(), HttpStatus.BAD_REQUEST));
	}

	@GetMapping("/ration-capacity")
	public Mono<ResponseEntity<State>> getStateCapacity(@PathVariable String stateName) {
		if (stateName != null) {
			return stateManagementService.getStateCapacity(stateName).map(state -> ResponseEntity.ok(state))
					.defaultIfEmpty(ResponseEntity.notFound().build());
		} else
			return Mono.just(new ResponseEntity<>(new State(), HttpStatus.BAD_REQUEST));
	}

	@GetMapping("/ration-request")
	public Mono<ResponseEntity<OrderRequest>> requestforRation(@RequestBody OrderRequestService orderRequest) {
		if (orderRequest != null) {
			return stateManagementService.requestforRation(orderRequest)
					.map(saveOrderRequest -> ResponseEntity.ok(saveOrderRequest))
					.defaultIfEmpty(ResponseEntity.notFound().build());
		} else
			return Mono.just(new ResponseEntity<>(new OrderRequest(), HttpStatus.BAD_REQUEST));
	}

	@PostMapping("/grant-order")
	public Mono<ResponseEntity<OrderGrant>> grantOrderNote(@RequestBody OrderGrantService grantOrder) {

		if (grantOrder != null) {
			return stateManagementService.grantOrderNote(grantOrder)
					.map(saveOrderRequest -> ResponseEntity.ok(saveOrderRequest))
					.defaultIfEmpty(ResponseEntity.notFound().build());
		} else
			return Mono.just(new ResponseEntity<>(new OrderGrant(), HttpStatus.BAD_REQUEST));

	}

}
