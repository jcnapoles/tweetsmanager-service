package com.acciona.tweetsmanager.service;

import java.util.List;

import com.acciona.tweetsmanager.model.Hashtag;

public interface HashtagService {

	List<Hashtag> findByCountUsedOrderDesc(Integer page);

	Hashtag createHashtag(Hashtag hashtag);
}
