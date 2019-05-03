package com.hcl.petpeersapp.persistance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pets")

public class Pets implements IEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "PET_NAME")
	private String name;

	@Column(name = "PET_AGE")
	private Integer age;

	@Column(name = "PET_PLACE")
	private String place;

	public Pets() {
	}

	@ManyToOne()
	@JoinColumn(name = "PET_OWNERID" ,nullable = true)
	private User user;

	public Pets(Long id) {
		this.id = id;
	}

	public Pets(Long id, String name, Integer age, String place, User user) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.place = place;
		this.user = user;
	}

	public Pets(Long id, String name, Integer age, String place) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.place = place;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
