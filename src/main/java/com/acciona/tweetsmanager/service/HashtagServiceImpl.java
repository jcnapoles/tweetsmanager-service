package com.acciona.tweetsmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.acciona.tweetsmanager.model.Hashtag;
import com.acciona.tweetsmanager.repository.HashtagRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

	@Autowired
	HashtagRepository hashtagRepository;

	@Override
	public List<Hashtag> findByCountUsedOrderDesc(Integer pageSize) {
		Pageable pageable = PageRequest.of(0, pageSize);
		return hashtagRepository.findByCountUsedOrderDesc(pageable);
	}

	@Override
	public Hashtag createHashtag(Hashtag hashtag) {
		Optional<Hashtag> hashtagOptional = hashtagRepository.findById(hashtag.getId());
		if (hashtagOptional.isPresent()) {
			return hashtagOptional.get();
		}
		Hashtag hashtagBD = hashtagRepository.save(hashtag);

		return hashtagBD;
	}

}
