package com.zerohunger.pdsmanagement.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "State")
public class State {
	
	@Id
	private String id;
	private String stateName;
	private Meta metaData;
	private Double capacity;

}
