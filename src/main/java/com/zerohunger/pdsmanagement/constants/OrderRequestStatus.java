package com.zerohunger.pdsmanagement.constants;

public enum OrderRequestStatus {

	PARTIALY_FULFILLED("PartialyFulfilled"), PENDING("Pending"), CRITICAL("Critical"), WITHDRAWN("Withdrawn"), FULFILLED("Fulfilled");

	private final String orderStatus;

	OrderRequestStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getRationItem() {
		return orderStatus;
	}
	
}
