package com.cocoe.spring.user.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import com.cocoe.spring.user.exception.EmailExistException;
import com.cocoe.spring.user.exception.EmailNotFoundException;
import com.cocoe.spring.user.exception.InvalidRoleException;
import com.cocoe.spring.user.exception.RecordNotFoundException;
import com.cocoe.spring.user.exception.UserNotFoundException;
import com.cocoe.spring.user.model.Role;
import com.cocoe.spring.user.model.User;


public interface UserDetailsService {
    User register(String firstName, String lastName, String email,String password,Collection<Role> roles) throws UserNotFoundException, EmailExistException, InvalidRoleException;
    List<User> getAllusers();
	User getuserById(Long id) throws RecordNotFoundException;
	void deleteuserByEmail(String email) throws RecordNotFoundException;
	User getUserByEmail(String email);
	User updateUser(String currentEmail, String firstName, String lastName, String email,
			String role) throws EmailExistException, UserNotFoundException, InvalidRoleException;
	void resetPassword(String email) throws EmailNotFoundException, MessagingException;
	
	

}