package com.cocoe.spring.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cocoe.spring.user.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
	
	Privilege findByName(String name);

}
