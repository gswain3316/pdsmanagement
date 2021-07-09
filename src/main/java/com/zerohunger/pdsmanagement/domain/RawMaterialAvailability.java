package com.zerohunger.pdsmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RawMaterialAvailability {
	
	private String rawMaterialName;
	private Double availableQuantity;

}
