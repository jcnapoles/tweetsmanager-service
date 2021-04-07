package com.acciona.tweetsmanager;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.acciona.tweetsmanager.client.TwitterClient;

@SpringBootApplication
@ComponentScan("com.acciona.tweetsmanager")
public class TweetsmanagerServiceApplication {

	@Autowired
	TwitterClient twitterClient;

	@PostConstruct
	public void initConsumingAPI() {
		twitterClient.streamingApi(1500);
	}

	public static void main(String[] args) {
		SpringApplication.run(TweetsmanagerServiceApplication.class, args);

	}

}
