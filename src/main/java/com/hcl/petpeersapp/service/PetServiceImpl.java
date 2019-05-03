package com.hcl.petpeersapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.petpeersapp.converter.IConverter;
import com.hcl.petpeersapp.domain.Pets;
import com.hcl.petpeersapp.exception.MessageCode;
import com.hcl.petpeersapp.exception.PetSoledOutFoundException;
import com.hcl.petpeersapp.exception.RecordNotFoundException;
import com.hcl.petpeersapp.persistance.repository.PetRepository;

@Service
public class PetServiceImpl implements PetService {
	@Autowired
	IConverter<Pets, com.hcl.petpeersapp.persistance.entity.Pets> petconverter;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PetRepository petRepository;

	@Override
	public Pets savePet(Pets pets) {
		try {
			return petconverter.convert(petRepository.save(petconverter.convert(pets)));
		} catch (Exception e) {

			throw new RuntimeException();
		}
	}

	@Override
	public List<Pets> getAllPets() {
		return petRepository.findAll().stream().map(u -> petconverter.convert(u)).collect(Collectors.toList());
	}

	@Override
	public Pets updatePets(Pets pets) {
		Optional<com.hcl.petpeersapp.persistance.entity.Pets> petEntity = petRepository.findById(pets.getId());
		if (petEntity.isPresent()) {
			petEntity.get().setName(pets.getName());
			petEntity.get().setAge(pets.getAge());
			petEntity.get().setPlace(pets.getPlace());
			if(pets.getUserId() != null)
				petEntity.get().setUser(new com.hcl.petpeersapp.persistance.entity.User(pets.getUserId()));
			
			return petconverter.convert(petRepository.save(petEntity.get()));

		} else {
			throw new RecordNotFoundException(MessageCode.PET_RECORD_NOT_FOUND);
		}
	}

	@Override
	public Pets getPetById(Long id) {
		Optional<com.hcl.petpeersapp.persistance.entity.Pets> petEntity = petRepository.findById(id);
		if (petEntity.isPresent()) {
			return petconverter.convert(petEntity.get());
		} else {
			throw new RecordNotFoundException(MessageCode.PET_RECORD_NOT_FOUND);
		}
	}

	@Override
	public Pets buyPet(Long petId, Long userId) {
		Pets pet = getPetById(petId);
		userService.getUserById(userId);
		if(pet.getUserId() == null) {
			pet.setUserId(userId);
			return updatePets(pet);
		}
		else{
			throw new PetSoledOutFoundException();
		}
			
	}

}
