package com.hcl.petpeersapp.exception;

public class PetSoledOutFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MessageCode ex;
	public PetSoledOutFoundException() {
		ex=MessageCode.SOLD_OUT_EXCEPTION;
	}
	
	public MessageCode getEx() {
		return ex;
	}
	public void setEx(MessageCode ex) {
		this.ex = ex;
	}
	public PetSoledOutFoundException(MessageCode ex) {
		this.ex=ex;
		
	}

}
