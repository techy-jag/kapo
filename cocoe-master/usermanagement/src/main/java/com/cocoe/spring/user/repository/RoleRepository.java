package com.cocoe.spring.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cocoe.spring.user.model.Role;
import com.cocoe.spring.user.model.User;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String role);

}
