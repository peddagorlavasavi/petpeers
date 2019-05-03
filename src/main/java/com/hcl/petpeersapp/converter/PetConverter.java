package com.hcl.petpeersapp.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hcl.petpeersapp.domain.Pets;
import com.hcl.petpeersapp.domain.User;

@Component
public class PetConverter implements IConverter<Pets, com.hcl.petpeersapp.persistance.entity.Pets> {
	@Autowired
	IConverter<User, com.hcl.petpeersapp.persistance.entity.User> userconverter;

	@Override
	public Pets convert(com.hcl.petpeersapp.persistance.entity.Pets k) {

		if (k.getUser() != null && k.getUser().getId() != null)
			return new Pets(k.getId(), k.getName(), k.getAge(), k.getPlace(), k.getUser().getId());
		else {
			return new Pets(k.getId(), k.getName(), k.getAge(), k.getPlace());
		}
	}

	@Override
	public com.hcl.petpeersapp.persistance.entity.Pets convert(Pets k) {

		if (k.getUserId() == null)
			return new com.hcl.petpeersapp.persistance.entity.Pets(k.getId(), k.getName(), k.getAge(), k.getPlace());
		else {
			com.hcl.petpeersapp.persistance.entity.User userEntity = new com.hcl.petpeersapp.persistance.entity.User(
					k.getUserId());
			return new com.hcl.petpeersapp.persistance.entity.Pets(k.getId(), k.getName(), k.getAge(), k.getPlace(),
					userEntity);
		}
	}

}
