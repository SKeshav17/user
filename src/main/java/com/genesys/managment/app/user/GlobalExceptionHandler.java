package com.genesys.managment.app.user;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.genesys.managment.app.user.exception.AppException;
import com.genesys.managment.app.user.exception.ErrorResponse;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(AppException.class)
	public ResponseEntity<?> handleAppException(AppException ex, WebRequest request){
		ErrorResponse errorResponse = new ErrorResponse(ex.getLocation(),ex.getDetails(),ex.getMoreDetails(),getCurrentDate());
		return ResponseEntity.status(HttpStatus.valueOf(ex.getStatusCode())).body(errorResponse);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleNonCustomizedException(Exception ex, WebRequest request){
		ErrorResponse errorResponse = new ErrorResponse("User Application","Unknown Error",ex.getMessage(),getCurrentDate());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
	
	private Date getCurrentDate() {
		LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * Add Exception Handler for methodfieldnotValidation
	 */
}
