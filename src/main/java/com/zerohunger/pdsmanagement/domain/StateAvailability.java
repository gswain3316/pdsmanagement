package com.zerohunger.pdsmanagement.domain;

import lombok.Data;

@Data
public class StateAvailability {

	private Long id;
	private Long stateId;
	private Long rawMaterialId;
	private Double availableQuantity;
	
}
