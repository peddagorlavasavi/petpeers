package com.hcl.petpeersapp.service;

import java.util.List;

import com.hcl.petpeersapp.domain.Pets;

public interface PetService {

	Pets savePet(Pets pets);

	List<Pets> getAllPets();

	Pets updatePets(Pets pets);

	Pets getPetById(Long id);
	Pets buyPet(Long petId,Long userId);

}
