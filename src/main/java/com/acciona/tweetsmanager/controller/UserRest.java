package com.acciona.tweetsmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acciona.tweetsmanager.model.User;
import com.acciona.tweetsmanager.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class UserRest {

	@Autowired
	UserRepository userRepository;

	/*********************** Retrieve All Users ***********************/
	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> getAllTweets() {
		log.debug("REST request to get all Users");
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(users);
	}

}
