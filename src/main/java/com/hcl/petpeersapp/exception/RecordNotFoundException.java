package com.hcl.petpeersapp.exception;

public class RecordNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MessageCode ex;
	public RecordNotFoundException() {
		ex=MessageCode.RECORD_NOT_FOUND;
	}
	
	public MessageCode getEx() {
		return ex;
	}
	public void setEx(MessageCode ex) {
		this.ex = ex;
	}
	public RecordNotFoundException(MessageCode ex) {
		this.ex=ex;
		
	}

}
