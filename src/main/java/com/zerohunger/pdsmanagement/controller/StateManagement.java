package com.zerohunger.pdsmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController("/pdsapp/state")
public class StateManagement {

	@GetMapping("/ration-availability")
	public Mono<ResponseEntity<?>> getRationAvailability(){
		return 
				null;
	}
	
	@GetMapping("/ration-capacity")
	public Mono<ResponseEntity<?>> getRationCapacity(){
		return 
				null;
	}
	
	
	
}
