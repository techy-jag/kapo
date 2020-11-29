package com.cocoe.spring.admin.service;

import java.util.List;

import com.cocoe.spring.user.model.User;

public interface AdminService {
	
	List<User> getAllUsers();
	List<User> getAllSeller();

}
