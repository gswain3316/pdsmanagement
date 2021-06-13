package com.zerohunger.pdsmanagement.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "StateAvailability")
@NoArgsConstructor
public class StateAvailability {

	@Id
	private String id;
	private String stateName;
	private List<Availability> availability;
	public StateAvailability(String stateName, List<Availability> availability) {
		super();
		this.stateName = stateName;
		this.availability = availability;
	}
	
}
