package com.acciona.tweetsmanager.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.acciona.tweetsmanager.service.TwitterClientService;

@Component
@Configuration
public class TwitterClient {

	@Autowired
	private TwitterClientService twitterClientService;

	public void streamingApi(Integer followers) {
		twitterClientService.streamingApi(followers);
	}

}
