package com.zerohunger.pdsmanagement.domain;

import java.util.Date;

import lombok.Data;

@Data
public class OrderRequest {

	private Long id;
	private Long requestingStateId;
	private Long rawMaterialId;
	private Double quantity;
	private String unitsRequested;
	private Boolean isActive;
	private Date createdOn;
	private Date modifiedOn;
	
}
