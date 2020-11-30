package com.cocoe.spring.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cocoe.spring.user.model.Role;
import com.cocoe.spring.user.repository.RoleRepository;
import com.cocoe.spring.user.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	@Override
	public Role getRoleByName(String role) {
		// TODO Auto-generated method stub
		return roleRepository.findByName(role);
	}

}
