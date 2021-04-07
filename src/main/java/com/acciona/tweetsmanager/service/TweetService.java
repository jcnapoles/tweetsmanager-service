package com.acciona.tweetsmanager.service;

import java.util.List;
import java.util.Optional;

import com.acciona.tweetsmanager.model.Tweet;
import com.acciona.tweetsmanager.model.User;

public interface TweetService {

	List<Tweet> findAllTweets();

	Optional<Tweet> getTweet(Long tweetId);

	Tweet updateTweet(Tweet tweet);

	List<Tweet> getTweetValidatedByUser(User user);

	Tweet createTweet(Tweet tweet);
}
