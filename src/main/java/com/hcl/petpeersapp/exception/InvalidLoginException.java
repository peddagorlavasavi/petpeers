package com.hcl.petpeersapp.exception;

public class InvalidLoginException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MessageCode ex;
	public InvalidLoginException() {
	ex=MessageCode.INVALID_LOGIN;
	}
	
	public MessageCode getEx() {
		return ex;
	}
	public void setEx(MessageCode ex) {
		this.ex = ex;
	}
	public InvalidLoginException(MessageCode ex) {
		this.ex=ex;
		
	}

}
