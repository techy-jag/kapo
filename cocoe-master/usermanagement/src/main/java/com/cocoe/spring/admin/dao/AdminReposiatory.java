package com.cocoe.spring.admin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cocoe.spring.user.model.Role;
import com.cocoe.spring.user.model.User;
import com.cocoe.spring.user.repository.UserRepository;

public interface AdminReposiatory extends UserRepository {	
	@Query( "select u from User u inner join u.roles r where r.name in :roles" )
	List<User> getAllUsers(@Param("roles") List<String> roles);

}
