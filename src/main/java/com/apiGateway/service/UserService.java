package com.apiGateway.service;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apiGateway.repository.UserRepo;
import com.apiGateway.security.PBKDF2Encoder;
import com.apiGateway.security.model.Role;
import com.apiGateway.security.model.User;

import reactor.core.publisher.Mono;

/**
 *
 * @author ard333
 */
@Service
public class UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	private PBKDF2Encoder passwordEncoder;
	
	private Map<String, User> data;

	//@PostConstruct  //added to create users at startup
	public void init() {
		data = new HashMap<>();

		// username:passwowrd -> user:user
		data.put("user",
				new User("user", "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=", true, Arrays.asList(Role.ROLE_USER)));

		// username:passwowrd -> admin:admin
		data.put("admin", new User("admin", "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=", true,
				Arrays.asList(Role.ROLE_ADMIN)));
	}

	public Mono<User> findByUsername(String username) {
		return userRepo.findById(username);
	}
	/*
	public Mono<User> saveUser(User user) {
		Mono<User> userMono = userRepo.save(user);
		System.out.println(userMono);
		return userMono;
	}
	*/

	public Mono<User> saveUser(User user) {
		String password = passwordEncoder.encode(user.getPassword());
		//User u = ;
		Mono<User> userMono = userRepo.save(new User(user.getUsername(), password, true, user.getRoles()));
		System.out.println(userMono);
		return userMono;
		
	}
	
}
