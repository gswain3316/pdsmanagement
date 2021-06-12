package com.zerohunger.pdsmanagement.domain;

import lombok.Data;

@Data
public class RequestStatus {

	private Long id;
	private Long requestId;
	private String status;
	private Double quantityFulfilled;
	
}
