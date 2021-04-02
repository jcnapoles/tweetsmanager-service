package com.acciona.tweetsmanager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acciona.tweetsmanager.model.Tweet;
import com.acciona.tweetsmanager.model.User;
import com.acciona.tweetsmanager.repository.UserRepository;
import com.acciona.tweetsmanager.service.TweetService;

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
			if (user.isEmpty()) {
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
		if (tweet.isEmpty()) {
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

}
