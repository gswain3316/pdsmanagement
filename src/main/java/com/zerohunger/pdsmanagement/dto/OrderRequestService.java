package com.zerohunger.pdsmanagement.dto;

import lombok.Data;

@Data
public class OrderRequestService {
	
	private String requestingStateName;
	private String rawMaterialName;
	private Double quantity;
	private String units;

}
