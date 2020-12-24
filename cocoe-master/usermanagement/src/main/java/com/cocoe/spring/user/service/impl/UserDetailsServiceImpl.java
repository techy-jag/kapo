package com.cocoe.spring.user.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cocoe.spring.user.exception.EmailExistException;
import com.cocoe.spring.user.exception.EmailNotFoundException;
import com.cocoe.spring.user.exception.InvalidRoleException;
import com.cocoe.spring.user.exception.RecordNotFoundException;
import com.cocoe.spring.user.exception.UserNotFoundException;
import com.cocoe.spring.user.model.Role;
import com.cocoe.spring.user.model.Seller;
import com.cocoe.spring.user.model.User;
import com.cocoe.spring.user.model.UserPrincipal;
import com.cocoe.spring.user.repository.RoleRepository;
import com.cocoe.spring.user.repository.UserRepository;
import com.cocoe.spring.user.repository.dao.UserAccountDetailsDAO;
import com.cocoe.spring.user.service.EmailService;
import com.cocoe.spring.user.service.LoginAttemptService;
import com.cocoe.spring.user.service.UserDetailsService;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserDetailsServiceImpl
		implements UserDetailsService, org.springframework.security.core.userdetails.UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private LoginAttemptService loginAttemptService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private RoleRepository roleRepository;


	@Override
	public User register(String firstName, 
			String lastName, String email, String password,Collection<Role> roles)
			throws UserNotFoundException, EmailExistException, InvalidRoleException {
		validateNewUserEmailWithRole(email,roles);
	
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setJoinDate(new Date());
		user.setPassword(encodePassword(password));
		user.setIsActive(true);
		user.setIsNotLocked(true);		
		user.setRoles( roles);		
		// user.setProfileImageUrl(getTemporaryProfileImageUrl(""));
	
		if(roles.stream().anyMatch(role->"ROLE_SELLER".equals(role.getName()))) {
			registerSeller(user);
		}else {
			userRepository.save(user);
		}
	
		return user;
	}

	
	private void registerSeller(User user) {
		Seller seller=new Seller(user);
		userRepository.save(seller);
		
	}


	@Override
	public List<User> getAllusers() {

		return userRepository.findAll();
	}

	@Override
	public User getuserById(Long id) throws RecordNotFoundException {

		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			return user.get();
		} else {
			throw new RecordNotFoundException("No user record exist for given id");
		}
	}

	@Override
	public void deleteuserByEmail(String email) throws RecordNotFoundException {
		User user = userRepository.findUserByEmail(email);

		if (Objects.nonNull(user)) {
			userRepository.deleteById(user.getId());
		} else {
			throw new RecordNotFoundException("No user record exist for given id");
		}

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findUserByEmail(username);

		if (Objects.isNull(user)) {
			logger.info("User not found with  id ", username);
			throw new UsernameNotFoundException("User not found with id " + username);
		} else {
			validateLoginAttempt(user);
			user.setLastLoginDateDisplay(user.getLastLoginDate());
			user.setLastLoginDate(new Date());
			UserPrincipal principal = new UserPrincipal(user);
			return principal;
		}

	}

	

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	@Override
	public User updateUser(String currentEmail, String firstName, String lastName,  String email,
			String role) throws EmailExistException, UserNotFoundException, InvalidRoleException {
		    User currentUser = validateEmailWithRole(currentEmail,  email,role);
	        currentUser.setFirstName(firstName);
	        currentUser.setLastName(lastName);	     
	        currentUser.setEmail(email);	        
	        currentUser.setRoles(Arrays.asList(roleRepository.findByName(role)));   	
	        userRepository.save(currentUser);	        
	        return currentUser;
	}

	

	@Override
	public void resetPassword(String email) throws EmailNotFoundException, MessagingException {
		 User user = userRepository.findUserByEmail(email);
	        if (user == null) {
	            throw new EmailNotFoundException("No user found with Emial " + email);
	        }
	        String password = RandomStringUtils.randomAlphanumeric(7);
	        user.setPassword(encodePassword(password));
	        userRepository.save(user);
	      
	        emailService.sendNewPasswordEmail(user.getFirstName(), password, user.getEmail());
	}
	private void validateLoginAttempt(User user) {
		if (user.getIsNotLocked()) {
			if (loginAttemptService.hasExceededMaxAttempts(user.getEmail())) {
				user.setIsNotLocked(false);
			} else {
				user.setIsNotLocked(true);
			}
		} else {
			loginAttemptService.evictUserFromLoginAttemptCache(user.getEmail());
		}
	}
	private void validateNewUserEmailWithRole(String email,Collection<Role> role) throws EmailExistException, InvalidRoleException {
			if (Objects.nonNull(getUserByEmail(email))) {
			throw new EmailExistException("Email already in use");
		}
	}

	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}
	private User validateEmailWithRole(String currentEmail, String email,String role) throws EmailExistException, UserNotFoundException, InvalidRoleException {
	
		User user=getUserByEmail(currentEmail);
		if (Objects.isNull(user)) {
			throw new UserNotFoundException("Please send correct user email to updatae ueser");
		}
		if (Objects.nonNull(getUserByEmail(email))) {
			throw new EmailExistException("Email already in use");
		}
		return user;
	}


	@Override
	public User  getCurrentUser() {
		return (User)SecurityContextHolder.getContext().
			getAuthentication().getPrincipal();
		
	}
	
	


	
}
