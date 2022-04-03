package com.genesys.managment.app.user.exception;

public class UserAuthenticationException extends AppException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAuthenticationException(String location, String moreDetails) {
		super(location,401,"Authentication is Failed",moreDetails);
	}

}
