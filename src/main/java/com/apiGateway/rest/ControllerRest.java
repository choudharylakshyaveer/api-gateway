package com.apiGateway.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apiGateway.security.model.User;
import com.apiGateway.service.UserService;

import reactor.core.publisher.Mono;

@CrossOrigin(maxAge = 3600, origins = "http://localhost:3000")
@RestController
public class ControllerRest {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public Mono<User>  saveUser(@RequestBody User user){
		Mono<User> a = userService.saveUser(user);
		System.out.println(user);
		return a;
		
	}
}
