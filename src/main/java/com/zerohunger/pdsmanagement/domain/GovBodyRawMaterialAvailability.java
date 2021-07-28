package com.zerohunger.pdsmanagement.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "GovBodyRawMaterialAvailability")
@NoArgsConstructor
@Schema(description = "Model to Get availability of all Rations for a particular State")
public class GovBodyRawMaterialAvailability {

	@Id
	private String id;
	@Schema(description = "Name of District")
	private String districtName;
	@Schema(description = "Name of State")
	private String stateName;
	@Schema(description = "Indicator to determine if Entity is State or District")
	private Boolean isStateIndicator;
	@Schema(description = "Availability of Ration Name along with Quantity")
	private List<RawMaterialAvailability> availability;
	public GovBodyRawMaterialAvailability(String stateName, List<RawMaterialAvailability> availability, Boolean isStateIndicator) {
		super();
		this.stateName = stateName;
		this.availability = availability;
		this.isStateIndicator = isStateIndicator;
	}

	//constructor without id
	public GovBodyRawMaterialAvailability(String stateName, List<RawMaterialAvailability> availability, String districtName, Boolean isStateIndicator) {
		super();
		this.stateName = stateName;
		this.availability = availability;
		this.districtName = districtName;
		this.isStateIndicator = isStateIndicator;
	}
	
}
