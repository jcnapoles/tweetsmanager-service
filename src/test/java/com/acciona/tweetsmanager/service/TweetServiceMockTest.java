package com.acciona.tweetsmanager.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.acciona.tweetsmanager.model.Hashtag;
import com.acciona.tweetsmanager.model.Language;
import com.acciona.tweetsmanager.model.Place;
import com.acciona.tweetsmanager.model.Tweet;
import com.acciona.tweetsmanager.model.User;
import com.acciona.tweetsmanager.repository.TweetRepository;

@SpringBootTest
class TweetServiceMockTest {

	@Mock
	private TweetRepository tweetRepository;

	private TweetService tweetService;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		tweetService = new TweetServiceImpl(tweetRepository);
		Set<Hashtag> hashtags = new HashSet<Hashtag>();
		hashtags.add(Hashtag.builder().text("development").build());
		Tweet tweet01 = Tweet.builder().language(Language.ES).text("Testing the text")
				.user(User.builder().id(2L).build()).place(Place.builder().id(3L).build()).hashtags(hashtags).build();

		Mockito.when(tweetRepository.findById(1L)).thenReturn(Optional.of(tweet01));
		Mockito.when(tweetRepository.save(tweet01)).thenReturn(tweet01);
	}

	@Test
	public void whenValidGetID_ThenReturnTweet() {
		Tweet found = tweetService.getTweet(1L).get();
		Assertions.assertThat(found.getText()).isEqualTo("Testing the text");
	}
}
