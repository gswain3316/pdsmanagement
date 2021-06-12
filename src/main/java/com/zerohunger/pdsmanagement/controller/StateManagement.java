package com.zerohunger.pdsmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zerohunger.pdsmanagement.domain.OrderGrant;
import com.zerohunger.pdsmanagement.domain.OrderRequest;
import com.zerohunger.pdsmanagement.domain.State;
import com.zerohunger.pdsmanagement.service.StateManagementService;

import reactor.core.publisher.Mono;

@RestController("/pdsapp/state")
public class StateManagement {

	@Autowired
	StateManagementService stateManagementService;

	@GetMapping("/ration-availability")
	public Mono<ResponseEntity<?>> getRationAvailability(@RequestParam String stateName) {

		if (stateName != null) {
			return null;
		} else
			return Mono.just(new ResponseEntity<>("Please Enter Correct State !", HttpStatus.BAD_REQUEST));

	}

	@GetMapping("/ration-capacity")
	public Mono<ResponseEntity<State>> getStateCapacity(@PathVariable String stateId) {
		return stateManagementService.getStateCapacity(stateId).map(state -> ResponseEntity.ok(state))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping("/ration-request")
	public Mono<ResponseEntity<OrderRequest>> requestforRation(@RequestBody OrderRequest orderRequest) {
		return stateManagementService.requestforRation(orderRequest)
				.map(saveOrderRequest -> ResponseEntity.ok(saveOrderRequest))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@PostMapping("/grant-order")
	public Mono<ResponseEntity<?>> grantOrderNote(@RequestBody OrderGrant grantOrder) {

		if (grantOrder != null) {
			return null;
		} else
			return Mono.just(new ResponseEntity<>("Please Enter the Grant Order Request !", HttpStatus.BAD_REQUEST));

	}

}
