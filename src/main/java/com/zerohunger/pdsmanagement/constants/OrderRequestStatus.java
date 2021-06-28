package com.zerohunger.pdsmanagement.constants;

public enum OrderRequestStatus {

	ACCEPTED_BY_STATE("AcceptedByState"), PENDING("Pending"), CRITICAL("Critical"), WITHDRAWN("Withdrawn"), FULLFILLED("Fullfilled");

	private final String orderStatus;

	OrderRequestStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getRationItem() {
		return orderStatus;
	}
	
}
