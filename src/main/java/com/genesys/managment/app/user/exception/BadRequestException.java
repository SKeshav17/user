package com.genesys.managment.app.user.exception;

public class BadRequestException extends AppException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadRequestException(String location, String moreDetails) {
		super(location,400,"Invalid Request",moreDetails);
	}

}
