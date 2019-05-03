package com.hcl.petpeersapp.testservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.any;

import com.hcl.petpeersapp.common.IConstants;
import com.hcl.petpeersapp.converter.IConverter;
import com.hcl.petpeersapp.exception.ConformPasswordFailureException;
import com.hcl.petpeersapp.exception.InvalidLoginException;
import com.hcl.petpeersapp.exception.RecordNotFoundException;
import com.hcl.petpeersapp.persistance.entity.User;
import com.hcl.petpeersapp.persistance.repository.UserRepository;
import com.hcl.petpeersapp.service.UserService;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@Autowired
	UserService userService;

	@MockBean
	UserRepository userRepository;

	@Autowired
	IConverter<com.hcl.petpeersapp.domain.User, User> userconverter;

	
	
	@Test
	public void whenValidName_thenUserShouldBeFound() {
		User alex = new User(1L, "alex", "abc@123");
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(alex));
		Long userId = 1L;
		com.hcl.petpeersapp.domain.User user = userService.getUserById(userId);
		assertThat(user.getUsername()).isEqualTo("alex");
	}

	@Test(expected = RecordNotFoundException.class)
	public void whenInValidName_thenErrorShouldThrowRecordNotFoundException() {
		User alex = new User(1L, "alex", "abc@123");
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(alex));
		Long userId = 2L;
		com.hcl.petpeersapp.domain.User user = userService.getUserById(userId);
		assertThat(user.getUsername()).isEqualTo("alex");
	}

	@Test
	public void whenValidId_thenUserShouldBeUpdated() {
		User alex = new User(1L, "alex", "abc@123");
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(alex));
		Mockito.when(userRepository.save(any(User.class))).thenReturn(alex);
		com.hcl.petpeersapp.domain.User user = userService.updateUser(userconverter.convert(alex));
		assertThat(user.getUsername()).isEqualTo("alex");
	}

	@Test(expected = RecordNotFoundException.class)
	public void whenInValidId_thenUserShouldBeThrowException() {
		User alex = new User(1L, "alex", "abc@123");
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(alex));
		alex.setId(2L);
		com.hcl.petpeersapp.domain.User user = userService.updateUser(userconverter.convert(alex));
		assertThat(user.getUsername()).isEqualTo("alex");
	}

	@Test
	public void whenValid_thenUserShouldBecreated() {
		User alex = new User(1L, "alex", "abc@123");
		Mockito.when(userRepository.save(any(User.class))).thenReturn(alex);
		com.hcl.petpeersapp.domain.User userdomain = userconverter.convert(alex);
		userdomain.setConfirmPassword("abc@123");
		com.hcl.petpeersapp.domain.User user = userService.createUser(userdomain);
		assertThat(user.getUsername()).isEqualTo("alex");
	}

	@Test(expected = ConformPasswordFailureException.class)
	public void whenInValidPasswordMatc_thenUserShouldthrowConformPasswordFailureException() {
		User alex = new User(1L, "alex", "abc@123");
		Mockito.when(userRepository.save(any(User.class))).thenReturn(alex);
		com.hcl.petpeersapp.domain.User userdomain = userconverter.convert(alex);
		userdomain.setConfirmPassword("abc@123r");
		com.hcl.petpeersapp.domain.User user = userService.createUser(userdomain);
		assertThat(user.getUsername()).isEqualTo("alex");
	}

	@Test
	public void whenValidId_thenUserShouldBeDeleted() {
		User alex = new User(1L, "alex", "abc@123");
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(alex));
		Mockito.doNothing().when(userRepository).deleteById(1L);
		com.hcl.petpeersapp.domain.User user = userService.deleteUser(1L);
		assertThat(user.getUsername()).isEqualTo("alex");
	}

	@Test
	public void whenValid_thengetAllUsers() {
		List<User> userList = new ArrayList<>();
		userList.add(new User(1L, "alex", "abc@123"));
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		List<com.hcl.petpeersapp.domain.User> user = userService.getAllUsers();
		assertThat(user.size()).isEqualTo(1);
	}
	@Test
	public void whenValid_LoginShouldSucess() {
		List<User> userList = new ArrayList<>();
		userList.add(new User(1L, "alex", "abc@123"));
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		String suceessmsg = userService.loginUser(userconverter.convert(new User(1L, "alex", "abc@123")));
		assertThat(suceessmsg).isEqualTo(IConstants.SUCCESS_LOGIN);
	}

	@Test(expected=InvalidLoginException.class)
	public void whenInValid_LoginShouldThrowInvalidLoginException() {
		List<User> userList = new ArrayList<>();
		userList.add(new User(1L, "alex1", "abc@123"));
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		String suceessmsg = userService.loginUser(userconverter.convert(new User(1L, "alex", "abc@123")));
		assertThat(suceessmsg).isEqualTo(IConstants.SUCCESS_LOGIN);
	}

}
