package com.cocoe.spring.admin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cocoe.spring.user.model.User;
import com.cocoe.spring.user.repository.UserRepository;

public interface AdminReposiatory extends UserRepository {
	@Query("select p from User p WHERE :role in elements(p.role)")
	List<User> getAllUsers(@Param("role") String role);

}
