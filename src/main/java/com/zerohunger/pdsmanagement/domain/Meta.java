package com.zerohunger.pdsmanagement.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Meta")
public class Meta {
	
	@Id
	private String id;
	private String spocName;
	private String spocEmail;
	public Meta(String spocName, String spocEmail) {
		super();
		this.spocName = spocName;
		this.spocEmail = spocEmail;
	}

}
