package com.zerohunger.pdsmanagement.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "OrderGrant")
public class OrderGrant {

	@Id
	private String id;
	private String grantingStateId;
	private String requestId;
	private Double quantity;
	private Date createdOn;
	private Date modifiedOn;
	
}
