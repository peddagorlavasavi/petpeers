package com.hcl.petpeersapp.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hcl.petpeersapp.persistance.entity.Pets;
		
@Repository
public interface PetRepository  extends JpaRepository<Pets, Long>{

}
