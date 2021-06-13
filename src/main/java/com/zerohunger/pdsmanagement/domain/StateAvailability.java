package com.zerohunger.pdsmanagement.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "StateAvailability")
@NoArgsConstructor
@Schema(description = "Model to Get availability of all Rations for a particular State")
public class StateAvailability {

	@Id
	private String id;
	@Schema(description = "Name of State")
	private String stateName;
	@Schema(description = "Availability of Ration Name along with Quantity")
	private List<Availability> availability;
	public StateAvailability(String stateName, List<Availability> availability) {
		super();
		this.stateName = stateName;
		this.availability = availability;
	}
	
}
