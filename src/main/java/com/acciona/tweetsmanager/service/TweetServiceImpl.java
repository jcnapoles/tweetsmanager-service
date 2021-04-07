package com.acciona.tweetsmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.acciona.tweetsmanager.model.Tweet;
import com.acciona.tweetsmanager.model.User;
import com.acciona.tweetsmanager.repository.TweetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

	private final TweetRepository tweetRepository;

	@Override
	public List<Tweet> findAllTweets() {
		return tweetRepository.findAll();
	}

	@Override
	public Optional<Tweet> getTweet(Long tweetId) {
		return tweetRepository.findById(tweetId);
	}

	@Override
	public Tweet updateTweet(Tweet tweet) {
		return tweetRepository.save(tweet);
	}

	@Override
	public List<Tweet> getTweetValidatedByUser(User user) {
		return tweetRepository.findByUserAndValidation(user, true);
	}

	@Override
	public Tweet createTweet(Tweet tweet) {
		Optional<Tweet> tweetOptional = tweetRepository.findById(tweet.getId());
		if (tweetOptional.isPresent()) {
			return tweetOptional.get();
		}
		tweet.setValidation(false);
		Tweet tweetBD = tweetRepository.save(tweet);

		return tweetBD;

	}

}
