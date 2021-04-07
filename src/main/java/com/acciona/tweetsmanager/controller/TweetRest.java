package com.acciona.tweetsmanager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.acciona.tweetsmanager.model.Tweet;
import com.acciona.tweetsmanager.model.User;
import com.acciona.tweetsmanager.repository.UserRepository;
import com.acciona.tweetsmanager.service.TweetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class TweetRest {

	@Autowired
	TweetService tweetService;

	@Autowired
	UserRepository userRepository;

	/*********************** Retrieve All Tweets or By User ***********************/
	@GetMapping(value = "/tweets")
	public ResponseEntity<List<Tweet>> getAllTweets(@RequestParam(name = "user", required = false) Long userId) {
		log.debug("REST request to retrieve validated tweets by user: {}", userId);
		List<Tweet> tweets = new ArrayList<Tweet>();

		if (null == userId) {
			tweets = tweetService.findAllTweets();
			if (tweets.isEmpty()) {
				return ResponseEntity.noContent().build();
			}

		} else {
			Optional<User> user = userRepository.findById(userId);
			if (!user.isPresent()) {
				return ResponseEntity.noContent().build();
			} else {
				tweets = tweetService.getTweetValidatedByUser(user.get());
				if (tweets.isEmpty()) {
					return ResponseEntity.noContent().build();
				}
			}

		}

		return ResponseEntity.ok(tweets);
	}

	/************************ Get Tweet By Id ************************/
	@GetMapping(value = "/tweets/{id}")
	public ResponseEntity<Tweet> getTweet(@PathVariable("id") Long id) {
		log.debug("REST request to get a Tweet by: {} ", id);
		Optional<Tweet> tweet = tweetService.getTweet(id);
		if (!tweet.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tweet.get());
	}

	/************************ Validate Tweet ************************/
	@PatchMapping(value = "/tweets/{id}")
	public ResponseEntity<Tweet> validateTweet(@PathVariable(value = "id", required = false) final Long id,
			@RequestBody Tweet tweet) {
		log.debug("REST request to validate a Tweet by: {}, {} ", id, tweet);

		if (tweet.getId() == null) {
			return ResponseEntity.badRequest().build();
		}
		if (!Objects.equals(id, tweet.getId())) {
			return ResponseEntity.badRequest().build();
		}

		if (null == tweetService.getTweet(id)) {
			return ResponseEntity.notFound().build();
		}

		Optional<Tweet> result = tweetService.getTweet(tweet.getId()).map(existingTweet -> {
			if (tweet.getValidation() != null) {
				existingTweet.setValidation(tweet.getValidation());
			}

			return existingTweet;
		}).map(tweetService::updateTweet);

		return ResponseEntity.ok(result.get());
	}

	/*
	 * -------------------Create a Tweet-------------------------------------------
	 */
	@PostMapping(value = "/tweets")
	public ResponseEntity<Tweet> createTweet(@Valid @RequestBody Tweet tweet, BindingResult result) {
		log.info("Creating Tweet : {}", tweet);
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
		}
		Tweet tweetBD = tweetService.createTweet(tweet);

		return ResponseEntity.status(HttpStatus.CREATED).body(tweetBD);
	}

	private String formatMessage(BindingResult result) {
		List<Map<String, String>> errors = result.getFieldErrors().stream().map(err -> {
			Map<String, String> error = new HashMap<>();
			error.put(err.getField(), err.getDefaultMessage());
			return error;

		}).collect(Collectors.toList());
		ErrorMessage errorMessage = ErrorMessage.builder().code("01").messages(errors).build();
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(errorMessage);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}

}
