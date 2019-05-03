package com.hcl.petpeersapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { RecordNotFoundException.class, RecordNotFoundException.class })
	protected ResponseEntity<RestError> handleConflict(RecordNotFoundException ex, WebRequest request) {
		return ResponseEntity.badRequest().body(new RestError(ex.getEx().getCode(), ex.getEx().getMessage()));
	}

	@ExceptionHandler(value = { InvalidLoginException.class, InvalidLoginException.class })
	protected ResponseEntity<RestError> handleConflict(InvalidLoginException ex, WebRequest request) {
		return ResponseEntity.badRequest().body(new RestError(ex.getEx().getCode(), ex.getEx().getMessage()));
	}

	@ExceptionHandler(value = { ConformPasswordFailureException.class, ConformPasswordFailureException.class })
	protected ResponseEntity<RestError> handleConflict(ConformPasswordFailureException ex, WebRequest request) {
		return ResponseEntity.badRequest().body(new RestError(ex.getEx().getCode(), ex.getEx().getMessage()));
	}
	
	@ExceptionHandler(value = { PetSoledOutFoundException.class, PetSoledOutFoundException.class })
	protected ResponseEntity<RestError> handleConflict(PetSoledOutFoundException ex, WebRequest request) {
		return ResponseEntity.badRequest().body(new RestError(ex.getEx().getCode(), ex.getEx().getMessage()));
	}
	
	@ExceptionHandler(value = { Exception.class, Exception.class })
	protected ResponseEntity<RestError> handleConflict(Exception ex, WebRequest request) {
		return ResponseEntity.badRequest().body(new RestError(001, "Exception occured"));
	}
	
}

class RestError {
	int code;
	String msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public RestError(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

}