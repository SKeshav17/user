package com.genesys.managment.app.user.exception;

public class ResourceNotException extends AppException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotException(String location, String moreDetails) {
		super(location,404,"Resource not Found",moreDetails);
	}

}
