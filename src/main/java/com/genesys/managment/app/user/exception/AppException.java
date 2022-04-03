package com.genesys.managment.app.user.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AppException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String location;
	
	private int statusCode;
	
	private String details;
	
	private String moreDetails;
	
	private AppException(String moreDetails) {
		super(moreDetails);
		this.moreDetails = moreDetails;
		
	}

	public AppException(String location, int statusCode, String details, String moreDetails) {
		this(moreDetails);
		this.location=location;
		this.statusCode=statusCode;
		this.details=details;
	}
	

}
