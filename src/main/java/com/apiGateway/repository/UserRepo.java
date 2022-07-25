package com.apiGateway.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.apiGateway.security.model.User;

public interface UserRepo extends ReactiveCrudRepository<User, String> {

}
