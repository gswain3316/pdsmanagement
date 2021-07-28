package com.zerohunger.pdsmanagement.controller;

import com.zerohunger.pdsmanagement.domain.GovBody;
import com.zerohunger.pdsmanagement.domain.GovBodyRawMaterialAvailability;
import com.zerohunger.pdsmanagement.domain.OrderGrant;
import com.zerohunger.pdsmanagement.domain.OrderRequest;
import com.zerohunger.pdsmanagement.domain.RequestStatus;
import com.zerohunger.pdsmanagement.dto.OrderGrantService;
import com.zerohunger.pdsmanagement.dto.OrderRequestService;
import com.zerohunger.pdsmanagement.service.DistrictManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/pdsapp/district")
@Slf4j
public class DistrictManagementController {

    @Autowired
    private DistrictManagementService districtManagementService;

    @GetMapping("/ration-availability")
	@Operation(description = "Get Ration Availability Data for a particular District")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "404", description = "No Response Found"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	public Mono<ResponseEntity<GovBodyRawMaterialAvailability>> getRationAvailability(@RequestParam String districtName) {
		if (districtName != null) {
			log.info("Ration Availability Controller Started !");
			return districtManagementService.getRationAvailability(districtName).map(District -> ResponseEntity.ok(District))
					.defaultIfEmpty(ResponseEntity.notFound().build());
		} else
			return Mono.just(new ResponseEntity<>(new GovBodyRawMaterialAvailability(), HttpStatus.BAD_REQUEST));
	}

	@GetMapping("/ration-capacity")
	@Operation(description = "Get Maximum Capacity(Fixed one value) for All Rations for a particular District")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "404", description = "No Response Found"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	public Mono<ResponseEntity<GovBody>> getDistrictCapacity(@RequestParam String districtName) {
		if (districtName != null) {
			return districtManagementService.getDistrictCapacity(districtName).map(District -> ResponseEntity.ok(District))
					.defaultIfEmpty(ResponseEntity.notFound().build());
		} else
			return Mono.just(new ResponseEntity<>(new GovBody(), HttpStatus.BAD_REQUEST));
	}

	@PostMapping("/ration-request")
	@Operation(description = "Publish/Post a Ration Request for a particular District")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "404", description = "No Response Found"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	public Mono<ResponseEntity<OrderRequest>> requestforRation(@RequestBody OrderRequestService orderRequest) {
		if (orderRequest != null) {
			log.info("Request for Ration Controller Hit !");
			return districtManagementService.requestforRation(orderRequest)
					.map(saveOrderRequest -> ResponseEntity.ok(saveOrderRequest))
					.defaultIfEmpty(ResponseEntity.notFound().build());
		} else
			return Mono.just(new ResponseEntity<>(new OrderRequest(), HttpStatus.BAD_REQUEST));
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
			return districtManagementService.grantOrderNote(grantOrder)
					.map(saveOrderRequest -> ResponseEntity.ok(saveOrderRequest))
					.defaultIfEmpty(ResponseEntity.notFound().build());
		} else
			return Mono.just(new ResponseEntity<>(new OrderGrant(), HttpStatus.BAD_REQUEST));

	}
	
	@GetMapping("/order-status")
	@Operation(description = "To Get Status of a Order Request for Ration by a District")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "404", description = "No Response Found"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	public Mono<ResponseEntity<RequestStatus>> getOrderStatus(@RequestParam String requestId) {
		if (requestId != null) {
			return districtManagementService.getOrderStatus(requestId)
					.map(saveOrderRequest -> ResponseEntity.ok(saveOrderRequest))
					.defaultIfEmpty(ResponseEntity.notFound().build());
		} else
			return Mono.just(new ResponseEntity<>(new RequestStatus(), HttpStatus.BAD_REQUEST));
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
		return "PDS App District Version is runnning fine !";
	}

}
