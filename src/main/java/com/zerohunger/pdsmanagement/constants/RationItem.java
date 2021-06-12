package com.zerohunger.pdsmanagement.constants;

public enum RationItem {

	RICE("Rice"), WHEAT("Wheat"), SUGAR("Sugar"), COARSE_GRAIN("CoarseGrain");

	private final String rationItem;

	RationItem(String rationItem) {
		this.rationItem = rationItem;
	}

	public String getRationItem() {
		return rationItem;
	}
}
