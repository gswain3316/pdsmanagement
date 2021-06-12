package com.zerohunger.pdsmanagement.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "StateAvailability")
public class StateAvailability {

	@Id
	private String id;
	private String stateId;
	private List<Availability> availability;
	public StateAvailability(String stateId, List<Availability> availability) {
		super();
		this.stateId = stateId;
		this.availability = availability;
	}
	
}
