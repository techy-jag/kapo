package com.cocoe.spring.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cocoe.spring.user.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String role);
	

}
