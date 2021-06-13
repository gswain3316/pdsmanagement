package com.zerohunger.pdsmanagement.domain;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "OrderGrant")
@NoArgsConstructor
public class OrderGrant {

	@Id
	private String id;
	private String grantingStateName;
	private String requestId;
	private Double quantity;
	
	//@CreatedDate
	private Date createdOn;
	
	//@LastModifiedDate 
	private Date modifiedOn;
	
	public OrderGrant(String grantingStateName, String requestId, Double quantity, Date createdOn, Date modifiedOn) {
		super();
		this.grantingStateName = grantingStateName;
		this.requestId = requestId;
		this.quantity = quantity;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
	}
	
}
