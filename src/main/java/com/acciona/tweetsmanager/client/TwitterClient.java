package com.acciona.tweetsmanager.client;

import org.springframework.beans.factory.annotation.Autowired;

import com.acciona.tweetsmanager.service.TwitterClientService;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class TwitterClient {
	
	
	@Autowired
	TwitterClientService twitterClientService;
	
	public void streamingApi() {
		twitterClientService.streamingApi();
	}

}
