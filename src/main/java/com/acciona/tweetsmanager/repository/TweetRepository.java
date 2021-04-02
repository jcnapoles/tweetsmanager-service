package com.acciona.tweetsmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acciona.tweetsmanager.model.Tweet;
import com.acciona.tweetsmanager.model.User;

public interface TweetRepository extends JpaRepository<Tweet, Long>{
	
	public List<Tweet> findByUserAndValidation(User user, Boolean validation);

}
