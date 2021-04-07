package com.acciona.tweetsmanager.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.acciona.tweetsmanager.model.Hashtag;

@DataJpaTest
class HashtagRepositoryMockTest {

	@Autowired
	private HashtagRepository hashtagRepository;

	@Test
	void whenFindAll_thenReturnListHashtag() {
		hashtagRepository.save(Hashtag.builder().text("development").build());

		List<Hashtag> founds = hashtagRepository.findAll();

		Assertions.assertThat(founds.size() > 0);
	}

}
