package com.hcl.petpeersapp.persistance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name="pet_user")

public class User implements IEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="USER_NAME")
	private String username;
	@Column(name="USER_PASSSSWORD")
	private String userPassword;
	public User() {
		
	}
	
	public User(Long id, String username, String userPassword) {
		super();
		this.id = id;
		this.username = username;
		this.userPassword = userPassword;
	}
	
	
	
	public User(Long id) {
		this.id = id;
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
