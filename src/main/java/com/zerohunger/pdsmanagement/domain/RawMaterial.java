package com.zerohunger.pdsmanagement.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.zerohunger.pdsmanagement.constants.RationItem;

import lombok.Data;

@Data
@Document(collection = "RawMaterial")
public class RawMaterial {

	@Id
	private String id;
	private RationItem rationItem;
	public RawMaterial(RationItem rationItem) {
		super();
		this.rationItem = rationItem;
	}
	
}
