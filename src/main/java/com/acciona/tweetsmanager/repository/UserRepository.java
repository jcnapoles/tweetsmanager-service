package com.acciona.tweetsmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acciona.tweetsmanager.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
