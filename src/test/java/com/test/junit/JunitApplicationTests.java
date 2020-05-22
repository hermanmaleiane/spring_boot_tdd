package com.test.junit;

import com.test.junit.model.User;
import com.test.junit.repository.UserRepository;
import com.test.junit.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class JunitApplicationTests {
	@Autowired
	private UserService service;

	@MockBean
	private UserRepository repository;

	@Test
	public void getUsersTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new User(376, "Junior", 19, "MOZ"), new User(39, "Nobel", 17, "RSA")).collect(Collectors.toList()));
		assertEquals(2, service.getUsers().size());
	}

	@Test
	public void getUserbyAddressTest() {
		String address = "MOZ";
		when(repository.findByAddress(address))
				.thenReturn(Stream.of(new User(376, "Junior", 31, "MOZ")).collect(Collectors.toList()));
		assertEquals(1, service.getUserbyAddress(address).size());
	}

	@Test
	public void saveUserTest() {
		User user = new User(999, "Taylor", 33, "Mahotas");
		when(repository.save(user)).thenReturn(user);
		assertEquals(user, service.addUser(user));
	}

	@Test
	public void deleteUserTest() {
		User user = new User(999, "Taylor", 33, "Mahotas");
		service.deleteUser(user);
		Mockito.verify(repository, times(1)).delete(user);
	}


}
