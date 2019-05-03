package com.hcl.petpeersapp.domain;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties
public class User implements IDomain {

	private Long id;

	@NotNull
	private String username;
	@NotNull
	private String userPassword;
	private String confirmPassword;
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(Long id, String username, String userPassword) {
		super();
		this.id = id;
		this.username = username;
		this.userPassword = userPassword;
	}

	public User(String username, String userPassword) {
		super();
		this.username = username;
		this.userPassword = userPassword;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
