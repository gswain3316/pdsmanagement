package com.zerohunger.pdsmanagement.domain;

import java.util.Date;

import lombok.Data;

@Data
public class OrderGrant {

	private Long id;
	private Long grantingStateId;
	private Long requestId;
	private Double quantity;
	private Date createdOn;
	private Date modifiedOn;
	
}
