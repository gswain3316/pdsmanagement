package com.zerohunger.pdsmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Model for Order Grant Request by a State for a particular Order request")
@AllArgsConstructor
@NoArgsConstructor
public class OrderGrantService {
	
	@Schema(description = "District who is Granting Ration to a Requesting District in the Same State")
	private String grantingDistrictName;
	@Schema(description = "State Name who is Granting Ration to another State")
	private String grantingStateName;
	@Schema(description = "Request ID for whom the Grant Order will be released")
	private String requestId;
	@Schema(description = "Quantity of the Ration granted by the State")
	private Double quantityGranted;

	public OrderGrantService(String grantingStateName, String requestId, Double quantityGranted) {
		this.grantingDistrictName = grantingStateName;
		this.requestId = requestId;
		this.quantityGranted = quantityGranted;
	}

}
