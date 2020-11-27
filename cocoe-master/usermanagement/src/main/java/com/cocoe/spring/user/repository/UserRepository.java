package com.cocoe.spring.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cocoe.spring.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findUserByEmail(String email);

}
