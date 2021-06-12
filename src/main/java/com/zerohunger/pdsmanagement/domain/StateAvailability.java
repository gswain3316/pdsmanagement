package com.zerohunger.pdsmanagement.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "StateAvailability")
public class StateAvailability {

	@Id
	private String id;
	private String stateId;
	private String rawMaterialId;
	private Double availableQuantity;
	
}
