package com.acciona.tweetsmanager.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.acciona.tweetsmanager.model.User;
import com.acciona.tweetsmanager.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
class UserRestIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	private List<User> userList;
	
	private Integer size;

	@BeforeEach
	public void setup() {
		this.userList = new ArrayList<>();
		userList.add(userRepository.save(User.builder().id(1L).name("User Number One").followersCount(1500).idStr("1")
				.screenName("user1").build()));
		userList.add(userRepository.save(User.builder().id(2L).name("User Number Two").followersCount(2500).idStr("2")
				.screenName("user2").build()));
		userList.add(userRepository.save(User.builder().id(3L).name("User Number Three").followersCount(3500).idStr("3")
				.screenName("user3").build()));
		size = userRepository.findAll().size();
	}

	@Test
	void shouldFetchAllUsers() throws Exception {
		this.mockMvc.perform(get("/api/users")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(size)));
	}

}
