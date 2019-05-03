package com.hcl.petpeersapp.exception;

public class ConformPasswordFailureException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MessageCode ex;
	public ConformPasswordFailureException() {
		ex=MessageCode.CONFIRM_PASSWORD_FAILURE;
	}
	public MessageCode getEx() {
		return ex;
	}
	public void setEx(MessageCode ex) {
		this.ex = ex;
	}
	public ConformPasswordFailureException(MessageCode ex) {
		this.ex=ex;
		
	}
	

}
