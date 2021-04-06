package com.acciona.tweetsmanager.client;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.acciona.tweetsmanager.model.Tweet;

import twitter4j.HashtagEntity;
import twitter4j.Status;

@Mapper
public interface StatusToTweet {
	@Mappings({
	      @Mapping(target="idStr", source="status.id"),
	      @Mapping(target="language", expression = "java(com.acciona.tweetsmanager.model.Language.valueOf(language.toUpperCase()))"),
	      @Mapping(target="user.idStr", source="status.user.id"),
	      @Mapping(target="validation", expression = "java(false)"),
	      @Mapping(target="place", source="status.place"),
	      @Mapping(target="place.id", ignore = true),
			@Mapping(target = "hashtags", source="hashtagEntity"),
			@Mapping(target = "hashtags.empty", ignore = true)
	    })
	Tweet statusToTweet(Status status, String language, HashtagEntity[] hashtagEntity);
}
