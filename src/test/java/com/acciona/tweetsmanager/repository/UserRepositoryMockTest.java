package com.acciona.tweetsmanager.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.acciona.tweetsmanager.model.User;

@DataJpaTest
class UserRepositoryMockTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	void whenFindAll_thenReturnListUser() {
		userRepository.save(User.builder().name("Juan").followersCount(1500).screenName("juancito01").build());

		List<User> founds = userRepository.findAll();

		Assertions.assertThat(founds.size() > 0);
	}

}
