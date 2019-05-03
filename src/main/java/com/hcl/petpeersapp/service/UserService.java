package com.hcl.petpeersapp.service;

import java.util.List;

import com.hcl.petpeersapp.domain.User;

public interface UserService {

	User createUser(User user);

	User updateUser(User user);

	User deleteUser(Long id);

	List<User> getAllUsers();

	User getUserById(Long Id);

	String loginUser( User user);

}
