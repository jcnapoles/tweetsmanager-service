package com.acciona.tweetsmanager.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acciona.tweetsmanager.client.StatusToTweet;
import com.acciona.tweetsmanager.model.Hashtag;
import com.acciona.tweetsmanager.model.Language;
import com.acciona.tweetsmanager.model.Place;
import com.acciona.tweetsmanager.model.Tweet;
import com.acciona.tweetsmanager.model.User;
import com.acciona.tweetsmanager.repository.HashtagRepository;
import com.acciona.tweetsmanager.repository.PlaceRepository;
import com.acciona.tweetsmanager.repository.TweetRepository;
import com.acciona.tweetsmanager.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Service
@RequiredArgsConstructor
public class TwitterClientServiceImpl implements TwitterClientService {

	private TwitterStream twitterStream;

	@Autowired
	TweetRepository tweetRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PlaceRepository placeRepository;

	@Autowired
	HashtagRepository hashtagrepository;

	@Override
	public void authenticate() {

	}

	@Override
	public void streamingApi(Integer followers) {
		StatusToTweet mapper = Mappers.getMapper(StatusToTweet.class);
		String languages = Arrays.toString(Language.values()).toLowerCase().replace("[", "").replace("]", "")
				.replaceAll(" ", "");
		twitterStream = new TwitterStreamFactory().getInstance().addListener(new StatusListener() {
			@Override
			public void onStatus(Status status) {
				if (followers == null) {
					if (status.getUser().getFollowersCount() >= 1500) {
						Tweet tweet = mapper.statusToTweet(status, status.getLang(), status.getHashtagEntities());
						User user = userRepository.save(tweet.getUser());
						tweet.setUser(user);
						Place place = tweet.getPlace();
						if (place != null) {
							placeRepository.save(place);
						}
						Set<Hashtag> hashtagList = new HashSet<Hashtag>();
						hashtagList = tweet.getHashtags();
						if (hashtagList.size() != 0) {
							hashtagrepository.saveAll(hashtagList);
						}
						tweetRepository.save(tweet);
						System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
					}
				} else {
					if (status.getUser().getFollowersCount() >= followers) {
						Tweet tweet = mapper.statusToTweet(status, status.getLang(), status.getHashtagEntities());
						User user = userRepository.save(tweet.getUser());
						tweet.setUser(user);
						Place place = tweet.getPlace();
						if (place != null) {
							placeRepository.save(place);
						}
						Set<Hashtag> hashtagList = new HashSet<Hashtag>();
						hashtagList = tweet.getHashtags();
						if (hashtagList.size() != 0) {
							hashtagrepository.saveAll(hashtagList);
						}
						tweetRepository.save(tweet);
						System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
					}
				}

			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				// System.out.println("Got a status deletion notice id:" +
				// statusDeletionNotice.getStatusId());
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				// System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:"
				// + upToStatusId);
			}

			@Override
			public void onStallWarning(StallWarning warning) {
				// System.out.println("Got stall warning:" + warning);
			}

			@Override
			public void onException(Exception ex) {
				ex.printStackTrace();
			}
		}).sample(languages);

	}

	@Override
	public void closeStreamingApi() {
		if (twitterStream != null) {
			twitterStream.shutdown();
		}

	}

}
