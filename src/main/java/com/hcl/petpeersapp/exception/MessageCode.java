package com.hcl.petpeersapp.exception;

public class MessageCode {

	private int code;
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageCode(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public static MessageCode RECORD_NOT_FOUND = new MessageCode(101, "Record not found");
	public static MessageCode INVALID_LOGIN = new MessageCode(102, "Invalid Credentials");
	public static MessageCode CONFIRM_PASSWORD_FAILURE = new MessageCode(103, "Password Missmatch");
	public static MessageCode SOLD_OUT_EXCEPTION = new MessageCode(104, "Pets already sold");
	public static MessageCode PET_RECORD_NOT_FOUND = new MessageCode(105, "Pet not found");
	public static MessageCode USER_RECORD_NOT_FOUND = new MessageCode(106, "User not found");
}



