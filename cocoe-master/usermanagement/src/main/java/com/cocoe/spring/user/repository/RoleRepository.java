package com.cocoe.spring.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cocoe.spring.user.model.Role;
import com.cocoe.spring.user.model.User;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String role);
	

}
