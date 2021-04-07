package com.acciona.tweetsmanager.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.acciona.tweetsmanager.model.Hashtag;
import com.acciona.tweetsmanager.model.Language;
import com.acciona.tweetsmanager.model.Place;
import com.acciona.tweetsmanager.model.Tweet;
import com.acciona.tweetsmanager.model.User;

@DataJpaTest
class TweetRepositoryMockTest {

	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PlaceRepository placeRepository;

	@Autowired
	private HashtagRepository hashtagRepository;

	@Test
	public void whenFindAll_thenReturnListTweet() {
		Set<Hashtag> hashtags = new HashSet<Hashtag>();
		hashtags.add(hashtagRepository.save(Hashtag.builder().text("development").build()));
		Tweet tweet01 = Tweet.builder().language(Language.ES).text("Testing the text")
				.user(userRepository.save(User.builder().id(2L).build()))
				.place(placeRepository.save(Place.builder().id(3L).build())).hashtags(hashtags).build();
		tweetRepository.save(tweet01);

		List<Tweet> founds = tweetRepository.findAll();

		Assertions.assertThat(founds.size() > 0);
	}

}
