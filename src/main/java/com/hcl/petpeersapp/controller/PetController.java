package com.hcl.petpeersapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.petpeersapp.domain.Pets;
import com.hcl.petpeersapp.service.PetService;

import io.swagger.annotations.ApiOperation;

@RestController

public class PetController {
	@Autowired
	PetService petService;

	@GetMapping("/pets")
	public List<Pets> getAllPets() {
		return petService.getAllPets();
	}

	@GetMapping("/pets/{id}")
	public Pets getPetById(@PathVariable Long id) {
		return petService.getPetById(id);
	}

	@PutMapping("/pets/{id}")
	public Pets updateById(@RequestBody Pets pets, @PathVariable Long id) {
		pets.setId(id);
		return petService.updatePets(pets);
	}

	@ApiOperation(value = "Buy the pet with pet & user id ")
	@PutMapping("/pets/{id}/buyPet/{userId}")
	public Pets buyById(@PathVariable Long id, @PathVariable Long userId) {
		return petService.buyPet(id, userId);
	}

	@PostMapping("/addPets")
	public Pets createPet(@RequestBody Pets pets) {

		return petService.savePet(pets);
	}

}
