package com.zerohunger.pdsmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Model for Order Request by a State who needs Ration from another State")
public class OrderRequestService {
	
	@Schema(description = "State Name who is requesting for Ration to all the States who can Grant")
	private String requestingStateName;
	@Schema(description = "Name of the Ration")
	private String rawMaterialName;
	@Schema(description = "Quantity of Ration Needed by the State")
	private Double quantity;
	@Schema(description = "Unit in which Ration Needed - KG/Quintal/Gram")
	private String units;

}
