package com.hcl.petpeersapp.converter;

import org.springframework.stereotype.Component;

import com.hcl.petpeersapp.domain.User;

@Component
public class UserConverter implements IConverter<User, com.hcl.petpeersapp.persistance.entity.User> {

	@Override
	public com.hcl.petpeersapp.persistance.entity.User convert(User d) {
		return new com.hcl.petpeersapp.persistance.entity.User(d.getId(), d.getUsername(), d.getUserPassword());
	}

	@Override
	public User convert(com.hcl.petpeersapp.persistance.entity.User d) {
		return new User(d.getId(), d.getUsername(), d.getUserPassword());
	}

}
