package com.zerohunger.pdsmanagement.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "State")
@NoArgsConstructor
public class State {
	
	@Id
	private String id;
	private String stateName;
	private Meta metaData;
	private Double capacity;
	public State(String stateName, Meta metaData, Double capacity) {
		super();
		this.stateName = stateName;
		this.metaData = metaData;
		this.capacity = capacity;
	}

}
