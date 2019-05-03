package com.hcl.petpeersapp.testservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.hcl.petpeersapp.converter.IConverter;
import com.hcl.petpeersapp.exception.RecordNotFoundException;
import com.hcl.petpeersapp.persistance.entity.Pets;
import com.hcl.petpeersapp.persistance.repository.PetRepository;
import com.hcl.petpeersapp.service.PetService;
import com.hcl.petpeersapp.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PetsServiceTest {
	@Autowired
	PetService petsService;

	@MockBean
	PetRepository petRepository;

	@MockBean
	UserService userService;
	
	@Autowired
	IConverter<com.hcl.petpeersapp.domain.Pets, Pets> petconverter;

	@Test
	public void whenValid_thenPetShouldBecreated() {
		Pets dog = new Pets(1L, "Dog", 12, "bng");
		Mockito.when(petRepository.save(any(Pets.class))).thenReturn(dog);
		com.hcl.petpeersapp.domain.Pets petdomain = petconverter.convert(dog);
		com.hcl.petpeersapp.domain.Pets pet = petsService.savePet(petdomain);
		assertThat(pet.getName()).isEqualTo("Dog");
	}

	@Test
	public void whenValidId_thenPetShouldBeFound() {
		Pets dog = new Pets(1L, "Dog", 12, "bng");
		Mockito.when(petRepository.findById(1L)).thenReturn(Optional.of(dog));
		Long petid = 1L;
		com.hcl.petpeersapp.domain.Pets pet = petsService.getPetById(petid);
		assertThat(pet.getName()).isEqualTo("Dog");
	}

	@Test(expected = RecordNotFoundException.class)
	public void whenInValidName_thenErrorShouldThrowRecordNotFoundException() {
		Pets dog = new Pets(1L, "Dog", 12, "bng");
		Mockito.when(petRepository.findById(1L)).thenReturn(Optional.of(dog));
		Long petid = 2L;
		com.hcl.petpeersapp.domain.Pets pet = petsService.getPetById(petid);
		assertThat(pet.getName()).isEqualTo("Dog");
	}

	@Test
	public void whenValidId_thenPetShouldBeUpdated() {
		Pets dog = new Pets(1L, "Dog", 12, "bng");
		Mockito.when(petRepository.findById(1L)).thenReturn(Optional.of(dog));
		Mockito.when(petRepository.save(any(Pets.class))).thenReturn(dog);
		com.hcl.petpeersapp.domain.Pets pet = petsService.updatePets(petconverter.convert(dog));
		assertThat(pet.getName()).isEqualTo("Dog");
	}

	@Test(expected = RecordNotFoundException.class)
	public void whenInValidId_thenUserShouldBeThrowException() {
		Pets dog = new Pets(1L, "Dog", 12, "bng");
		Mockito.when(petRepository.findById(1L)).thenReturn(Optional.of(dog));
		Mockito.when(petRepository.save(any(Pets.class))).thenReturn(dog);
		dog.setId(2L);
		com.hcl.petpeersapp.domain.Pets pet = petsService.updatePets(petconverter.convert(dog));
		assertThat(pet.getName()).isEqualTo("Dog");
	}
	@Test
	public void whenValid_thengetAllPets() {
		List<Pets> petList = new ArrayList<>();
		petList.add(new Pets(1L, "Dog", 12, "bng"));
		Mockito.when(petRepository.findAll()).thenReturn(petList);
		List<com.hcl.petpeersapp.domain.Pets> pets = petsService.getAllPets();
		assertThat(pets.size()).isEqualTo(1);
	}
	
	@Test
	public void whenValid_thenBuyPet() {
		Pets dog = new Pets(1L, "Dog", 12, "bng");
		Mockito.when(petRepository.findById(1L)).thenReturn(Optional.of(dog));
		Mockito.when(userService.getUserById(1L)).thenReturn(new com.hcl.petpeersapp.domain.User());
		Mockito.when(petRepository.save(any(Pets.class))).thenReturn(dog);
		com.hcl.petpeersapp.domain.Pets pets = petsService.buyPet(1L, 1L);
		assertThat(pets.getName()).isEqualTo("Dog");
		assertThat(pets.getUserId()).isEqualTo(1L);
	}

}
