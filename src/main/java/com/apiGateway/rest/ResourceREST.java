package com.apiGateway.rest;


import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apiGateway.repository.model.Message;
import com.apiGateway.security.model.User;
import com.apiGateway.service.UserService;

import reactor.core.publisher.Mono;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ResourceREST {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/resource/user", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER')")
	public Mono<ResponseEntity<?>> user() {
		return Mono.just(ResponseEntity.ok(new Message("Content for user")));
	}
	
	@RequestMapping(value = "/resource/admin", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public Mono<ResponseEntity<?>> admin() {
		return Mono.just(ResponseEntity.ok(new Message("Content for admin")));
	}
	
	@RequestMapping(value = "/resource/user-or-admin", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public Mono<ResponseEntity<?>> userOrAdmin() {
		return Mono.just(ResponseEntity.ok(new Message("Content for user or admin")));
	}
	
	@RequestMapping(value = "/user/save", method = RequestMethod.GET)
	public Mono<User>  saveUser(@RequestBody User user){
		Mono<User> user1 =  userService.saveUser(user);
		System.out.println(user1);
		return user1;
		
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public Mono<ResponseEntity<?>>  testApi(){
		
		Mono<String> test = Mono.just("Test Data from API");
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.set("Access-Control-Allow-Origin", "*");
	    responseHeaders.set("custom_header", "header value");
		return Mono.just(ResponseEntity.ok().headers(responseHeaders).body(test));
		
		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public Mono<ResponseEntity<?>>  testPostApi(@RequestBody User user){
		Mono<String> test = Mono.just("Test Data from Post API");
		System.out.println(user.getUsername());
		return Mono.just(ResponseEntity.ok().body(test));
	}
	
	
}
