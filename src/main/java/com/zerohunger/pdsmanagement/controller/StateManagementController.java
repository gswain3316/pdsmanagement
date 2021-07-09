package com.zerohunger.pdsmanagement.controller;

import com.zerohunger.pdsmanagement.domain.GovBody;
import com.zerohunger.pdsmanagement.domain.GovBodyRawMaterialAvailability;
import com.zerohunger.pdsmanagement.domain.OrderGrant;
import com.zerohunger.pdsmanagement.domain.OrderRequest;
import com.zerohunger.pdsmanagement.domain.RequestStatus;
import com.zerohunger.pdsmanagement.dto.OrderGrantService;
import com.zerohunger.pdsmanagement.dto.OrderRequestService;
import com.zerohunger.pdsmanagement.service.StateManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pdsapp/state")
@Slf4j
public class StateManagementController {

	@Autowired
	private StateManagementService stateManagementService;

	@GetMapping("/ration-availability")
	@Operation(description = "Get Ration Availability Data for a particular State")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "404", description = "No Response Found"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	public Mono<ResponseEntity<GovBodyRawMaterialAvailability>> getRationAvailability(@RequestParam String stateName) {
		if (stateName != null) {
			log.info("Ration Availability Controller Started !");
			return stateManagementService.getRationAvailability(stateName).map(state -> ResponseEntity.ok(state))
					.defaultIfEmpty(ResponseEntity.notFound().build());
		} else
			return Mono.error(new IllegalArgumentException("State Name is not provided"));
	}

	@GetMapping("/ration-capacity")
	@Operation(description = "Get Maximum Capacity(Fixed one value) for All Rations for a particular State")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "404", description = "No Response Found"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	public Mono<ResponseEntity<GovBody>> getStateCapacity(@RequestParam String stateName) {
		if (stateName != null) {
			return stateManagementService.getStateCapacity(stateName).map(state -> ResponseEntity.ok(state))
					.defaultIfEmpty(ResponseEntity.notFound().build());
		} else
			return Mono.error(new IllegalArgumentException("State Name is not provided"));
	}

	@PostMapping("/ration-request")
	@Operation(description = "Publish/Post a Ration Request for a particular State")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "404", description = "No Response Found"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	public Mono<ResponseEntity<OrderRequest>> requestforRation(@RequestBody OrderRequestService orderRequest) {
		if (orderRequest != null) {
			log.info("Request for Ration Controller Hit !");
			return stateManagementService.requestforRation(orderRequest)
					.map(saveOrderRequest -> ResponseEntity.ok(saveOrderRequest))
					.defaultIfEmpty(ResponseEntity.notFound().build());
		} else
			return Mono.error(new IllegalArgumentException("Order Request is not provided"));
	}

	@PostMapping("/grant-order")
	@Operation(description = "Publish/Post a Ration Grant Request for a particular Ration Request")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "404", description = "No Response Found"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	public Mono<ResponseEntity<OrderGrant>> grantOrderNote(@RequestBody OrderGrantService grantOrder) {

		if (grantOrder != null) {
			return stateManagementService.grantOrderNote(grantOrder)
					.map(saveOrderRequest -> ResponseEntity.ok(saveOrderRequest))
					.defaultIfEmpty(ResponseEntity.notFound().build());
		} else
			return Mono.error(new IllegalArgumentException("Grant Order is not provided"));

	}
	
	@GetMapping("/order-status")
	@Operation(description = "To Get Status of a Order Request for Ration by a State")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "404", description = "No Response Found"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	public Mono<ResponseEntity<RequestStatus>> getOrderStatus(@RequestParam String requestId) {
		if (requestId != null) {
			return stateManagementService.getOrderStatus(requestId)
					.map(saveOrderRequest -> ResponseEntity.ok(saveOrderRequest))
					.defaultIfEmpty(ResponseEntity.notFound().build());
		} else
			return Mono.error(new IllegalArgumentException("Request Id is not provided"));
	}
	
	@GetMapping
	@Operation(description = "To Test if the Controller or API is working")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "404", description = "No Response Found"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	public String welcome() {
		return "PDS App State Version is runnning fine !";
	}

}
