package com.genesys.managment.app.user.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

	private String location;
	
	private String details;
	
	private String moreDetails;
	
	private Date timestamp;
}
