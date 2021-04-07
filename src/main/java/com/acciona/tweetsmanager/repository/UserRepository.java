package com.acciona.tweetsmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acciona.tweetsmanager.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
