package com.hcl.petpeersapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.petpeersapp.common.IConstants;
import com.hcl.petpeersapp.converter.IConverter;
import com.hcl.petpeersapp.domain.User;
import com.hcl.petpeersapp.exception.ConformPasswordFailureException;
import com.hcl.petpeersapp.exception.InvalidLoginException;
import com.hcl.petpeersapp.exception.MessageCode;
import com.hcl.petpeersapp.exception.RecordNotFoundException;
import com.hcl.petpeersapp.persistance.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	IConverter<User, com.hcl.petpeersapp.persistance.entity.User> userconverter;

	@Override
	public User createUser(User user) {
			if (user.getConfirmPassword() != null && user.getUserPassword().equals(user.getConfirmPassword()))
				return userconverter.convert(userRepository.save(userconverter.convert(user)));
			else
				throw new ConformPasswordFailureException();
	}

	@Override
	public User updateUser(User user) {
		Optional<com.hcl.petpeersapp.persistance.entity.User> userEntity = userRepository.findById(user.getId());
		if (userEntity.isPresent()) {
			userEntity.get().setUsername(user.getUsername());
			userEntity.get().setUserPassword(user.getUserPassword());
			return userconverter.convert(userRepository.save(userEntity.get()));

		} else {
			throw new RecordNotFoundException(MessageCode.USER_RECORD_NOT_FOUND);
		}
	}

	@Override
	public User deleteUser(Long id) {
		User user = getUserById(id);
		userRepository.deleteById(id);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll().stream().map(u -> userconverter.convert(u)).collect(Collectors.toList());
	}

	@Override
	public User getUserById(Long id) {
		Optional<com.hcl.petpeersapp.persistance.entity.User> userEntity = userRepository.findById(id);
		if (userEntity.isPresent()) {
			return userconverter.convert(userEntity.get());
		} else {
			throw new RecordNotFoundException(MessageCode.USER_RECORD_NOT_FOUND);
		}
	}

	@Override
	public String loginUser(User user) {
		boolean isvalidUser = false;
		for (User u : getAllUsers()) {
			if (u.getUsername().equals(user.getUsername()) && u.getUserPassword().equals(user.getUserPassword()))
				isvalidUser = true;
		}
		if (isvalidUser)
			return IConstants.SUCCESS_LOGIN;
		else
			throw new InvalidLoginException();

	}

}
