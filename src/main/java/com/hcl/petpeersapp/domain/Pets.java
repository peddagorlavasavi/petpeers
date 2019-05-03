package com.hcl.petpeersapp.domain;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties
public class Pets implements IDomain {

	private Long id;
	@NotNull
	private String name;
	@NotNull
	private Integer age;
	@NotNull
	private String place;
	
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Pets() {
	}

	public Pets(Long id, String name, Integer age, String place, Long userId) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.userId = userId;
		this.place = place;
	}

	public Pets(Long id,String name, Integer age, String place) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.place = place;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

}
