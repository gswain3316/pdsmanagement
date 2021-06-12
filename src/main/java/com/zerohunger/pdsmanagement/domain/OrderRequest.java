package com.zerohunger.pdsmanagement.domain;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "OrderRequest")
public class OrderRequest {

	@Id
	private String id;
	private String requestingStateId;
	private String rawMaterialId;
	private Double quantity;
	private String unitsRequested;
	private Boolean isActive;

	@CreatedDate
	private Date createdOn;
	
	@LastModifiedDate 
	private Date modifiedOn;
	public OrderRequest(String requestingStateId, String rawMaterialId, Double quantity, String unitsRequested,
			Boolean isActive, Date createdOn, Date modifiedOn) {
		super();
		this.requestingStateId = requestingStateId;
		this.rawMaterialId = rawMaterialId;
		this.quantity = quantity;
		this.unitsRequested = unitsRequested;
		this.isActive = isActive;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
	}
	
}
