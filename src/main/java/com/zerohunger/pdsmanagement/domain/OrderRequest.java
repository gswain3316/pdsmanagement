package com.zerohunger.pdsmanagement.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "OrderRequest")
@NoArgsConstructor
public class OrderRequest {

	@Id
	private String id;
	private String requestingDistrictName;
	private String requestingStateName;
	private Boolean isStateIndicator;
	private String rawMaterialName;
	private Double quantity;
	private String unitsRequested;
	private Boolean isActive;

	//@CreatedDate
	private Date createdOn;
	
	//@LastModifiedDate 
	private Date modifiedOn;
	
	public OrderRequest(String requestingStateName, String rawMaterialName, Double quantity, String unitsRequested,
			Boolean isActive, Date createdOn, Date modifiedOn, Boolean isStateIndicator) {
		super();
		this.requestingStateName = requestingStateName;
		this.rawMaterialName = rawMaterialName;
		this.quantity = quantity;
		this.unitsRequested = unitsRequested;
		this.isActive = isActive;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.isStateIndicator = isStateIndicator;
	}

	//constructor with all fields except id
	public OrderRequest(String requestingStateName, String rawMaterialName, Double quantity, String unitsRequested,	Boolean isActive, Date createdOn, Date modifiedOn, String requestingDistrictName, Boolean isStateIndicator) {
		super();
		this.requestingStateName = requestingStateName;
		this.rawMaterialName = rawMaterialName;
		this.quantity = quantity;
		this.unitsRequested = unitsRequested;
		this.isActive = isActive;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.requestingDistrictName = requestingDistrictName;
		this.isStateIndicator = isStateIndicator;
	}
	
}
