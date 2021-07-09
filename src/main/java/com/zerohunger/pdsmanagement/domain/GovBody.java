package com.zerohunger.pdsmanagement.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "GovBody")
@NoArgsConstructor
@Schema(description = "Model to describe Government Body i.e State or District")
public class GovBody {
	
	@Id
	private String id;
	@Schema(description = "Name of the District")
	private String districtName;
	@Schema(description = "Name of State")
	private String stateName;
	@Schema(description = "Point of Contact for the Government Body/ SPOC")
	private Meta metaData;
	@Schema(description = "Total Capacity of the State/ District to hold Rations")
	private Double capacity;
	public GovBody(String stateName, Meta metaData, Double capacity) {
		super();
		this.stateName = stateName;
		this.metaData = metaData;
		this.capacity = capacity;
	}

	//consructor without id
	public GovBody(String stateName, Meta metaData, Double capacity, String districtName) {
		super();
		this.stateName = stateName;
		this.metaData = metaData;
		this.capacity = capacity;
		this.districtName = districtName;
	}

}
