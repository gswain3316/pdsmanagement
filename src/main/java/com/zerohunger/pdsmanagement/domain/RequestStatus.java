package com.zerohunger.pdsmanagement.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "RequestStatus")
public class RequestStatus {

	@Id
	private String id;
	private String requestId;
	private String status;
	private Double quantityFulfilled;
	
}
