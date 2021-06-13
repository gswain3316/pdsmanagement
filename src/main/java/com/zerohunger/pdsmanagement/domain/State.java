package com.zerohunger.pdsmanagement.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "State")
@NoArgsConstructor
@Schema(description = "Model to describe State")
public class State {
	
	@Id
	private String id;
	@Schema(description = "Name of State")
	private String stateName;
	@Schema(description = "Point of Contact for the State/ SPOC")
	private Meta metaData;
	@Schema(description = "Total Capacity of State to hold Rations")
	private Double capacity;
	public State(String stateName, Meta metaData, Double capacity) {
		super();
		this.stateName = stateName;
		this.metaData = metaData;
		this.capacity = capacity;
	}

}
