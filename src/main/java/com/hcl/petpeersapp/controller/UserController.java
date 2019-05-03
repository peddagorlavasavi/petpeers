package com.hcl.petpeersapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.petpeersapp.domain.User;
import com.hcl.petpeersapp.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	@PostMapping("/register")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	@PostMapping("/login")
	public String loginUser(@RequestBody User user) {
		return userService.loginUser(user);
	}

	@PutMapping("/users/{id}")
	public User updateUser(@RequestBody User user, @PathVariable Long id) {
		user.setId(id);
		return userService.updateUser(user);
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}


	@DeleteMapping("/users/{id}")
	public User deleteById(@PathVariable Long id) {
		return userService.deleteUser(id);
	}
	@GetMapping("/users/{id}")
	public User getById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

}
