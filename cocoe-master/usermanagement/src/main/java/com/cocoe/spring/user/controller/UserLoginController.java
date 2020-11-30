package com.cocoe.spring.user.controller;

import static com.cocoe.spring.user.auth.jwt.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.OK;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cocoe.spring.user.auth.jwt.JWTTokenProvider;
import com.cocoe.spring.user.dto.UserDTO;
import com.cocoe.spring.user.dto.mapper.UserToUserDTOMapper;
import com.cocoe.spring.user.exception.EmailExistException;
import com.cocoe.spring.user.exception.InvalidRoleException;
import com.cocoe.spring.user.exception.UserNotFoundException;
import com.cocoe.spring.user.exception.UsernameExistException;
import com.cocoe.spring.user.model.Role;
import com.cocoe.spring.user.model.User;
import com.cocoe.spring.user.model.UserPrincipal;
import com.cocoe.spring.user.service.RoleService;
import com.cocoe.spring.user.service.UserDetailsService;

@RestController
@RequestMapping("/user")


public class UserLoginController {
	@Autowired
	private UserDetailsService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTTokenProvider jwtTokenProvider;
	@Autowired
	private RoleService roleService;

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) {
		authenticate(user.getEmail(), user.getPassword());
		User loginUser = userService.getUserByEmail(user.getEmail());
		UserPrincipal userPrincipal = new UserPrincipal(loginUser);
		HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
		return new ResponseEntity<>(loginUser, jwtHeader, OK);
	}

	@PostMapping("/register")
	public ResponseEntity<UserDTO> register(@RequestBody User user)
			throws UserNotFoundException, UsernameExistException, EmailExistException, InvalidRoleException {		
		Collection<Role> roles=Arrays.asList(roleService.getRoleByName("ROLE_USER"));
		User newUser = userService.register(user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getPassword(), roles);
		return new ResponseEntity<>(UserToUserDTOMapper.convert(newUser), OK);
	}

	private HttpHeaders getJwtHeader(UserPrincipal user) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(user));
		return headers;
	}

	private void authenticate(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}

}
