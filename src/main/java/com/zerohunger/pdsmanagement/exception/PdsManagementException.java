package com.zerohunger.pdsmanagement.exception;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PdsManagementException {

	@ExceptionHandler(value = EntityNotFoundException.class)
	public ResponseEntity<ErrorMsg> requestStatusError(EntityNotFoundException ex) {
		ErrorMsg msg = new ErrorMsg();
		msg.setErrorCode(404);
		msg.setErrorMsg(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}

	@ExceptionHandler(value = OrderRequestSaveError.class)
	public ResponseEntity<ErrorMsg> orderRequestSaveError(OrderRequestSaveError ex) {
		ErrorMsg msg = new ErrorMsg();
		msg.setErrorCode(400);
		msg.setErrorMsg(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
	}

	@ExceptionHandler(value = OrderGrantSaveError.class)
	public ResponseEntity<ErrorMsg> orderGrantSaveError(OrderGrantSaveError ex) {
		ErrorMsg msg = new ErrorMsg();
		msg.setErrorCode(400);
		msg.setErrorMsg(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
	}

	@ExceptionHandler(value = IncorrectResultSizeDataAccessException.class)
	public ResponseEntity<ErrorMsg> orderGrantSaveError(IncorrectResultSizeDataAccessException ex) {
		ErrorMsg msg = new ErrorMsg();
		msg.setErrorCode(400);
		msg.setErrorMsg(ex.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
	}

}
