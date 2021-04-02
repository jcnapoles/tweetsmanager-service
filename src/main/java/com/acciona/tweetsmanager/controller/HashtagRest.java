package com.acciona.tweetsmanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acciona.tweetsmanager.model.Hashtag;
import com.acciona.tweetsmanager.service.HashtagService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class HashtagRest {

	@Autowired
	HashtagService hashtagService;

	/***************** Retrieve Top N Hashtags ***************************/
	@GetMapping(value = "/hashtags")
	public ResponseEntity<List<Hashtag>> getTopHashTagsUsed(
			@RequestParam(name = "pageSize", required = false) Integer pageSize) {
		log.debug("REST request to retrieve TOP hashtags used: {}", pageSize);
		List<Hashtag> hashtags = new ArrayList<Hashtag>();
		if (null == pageSize) {
			pageSize = 10;
			hashtags = hashtagService.findByCountUsedOrderDesc(pageSize);
			if (hashtags.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		} else {
			hashtags = hashtagService.findByCountUsedOrderDesc(pageSize);
			if (hashtags.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		}

		return ResponseEntity.ok(hashtags);
	}
}
