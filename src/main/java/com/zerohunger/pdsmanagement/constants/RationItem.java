package com.zerohunger.pdsmanagement.constants;

public enum RationItem {

	Rice("Rice"), Wheat("Wheat"), Sugar("Sugar"), CoarseGrain("Coarse Grain");

	private final String rationItem;

	RationItem(String rationItem) {
		this.rationItem = rationItem;
	}

	public String getRationItem() {
		return rationItem;
	}
}
