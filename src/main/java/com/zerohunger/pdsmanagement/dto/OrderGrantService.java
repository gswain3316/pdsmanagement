package com.zerohunger.pdsmanagement.dto;

import lombok.Data;

@Data
public class OrderGrantService {
	
	private String grantingStateName;
	private String requestId;
	private Double quantityGranted;

}
