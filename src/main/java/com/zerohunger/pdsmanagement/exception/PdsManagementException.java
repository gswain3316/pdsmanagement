package com.zerohunger.pdsmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PdsManagementException {

	@ExceptionHandler(value = RequestStatusNotFoundException.class)
	public ResponseEntity<ErrorMsg> appException(RequestStatusNotFoundException ex){
		ErrorMsg msg = new ErrorMsg();
		msg.setErrorCode(404);
		msg.setErrorMsg(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg) ;
	}
	
}
