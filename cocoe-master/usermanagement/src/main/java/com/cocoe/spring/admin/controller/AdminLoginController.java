package com.cocoe.spring.admin.controller;

import static com.cocoe.spring.user.auth.jwt.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cocoe.spring.user.Role;
import com.cocoe.spring.user.auth.jwt.JWTTokenProvider;
import com.cocoe.spring.user.exception.EmailExistException;
import com.cocoe.spring.user.exception.InvalidRoleException;
import com.cocoe.spring.user.exception.UserNotFoundException;
import com.cocoe.spring.user.exception.UsernameExistException;
import com.cocoe.spring.user.model.User;
import com.cocoe.spring.user.model.UserPrincipal;
import com.cocoe.spring.user.service.UserDetailsService;

@RestController
@RequestMapping("/admin")


public class AdminLoginController {
	@Autowired
	private UserDetailsService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTTokenProvider jwtTokenProvider;

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) {
		authenticate(user.getEmail(), user.getPassword());
		User loginUser = userService.getUserByEmail(user.getEmail());
		UserPrincipal userPrincipal = new UserPrincipal(loginUser);
		HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
		return new ResponseEntity<>(loginUser, jwtHeader, OK);
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user)
			throws UserNotFoundException, UsernameExistException, EmailExistException, InvalidRoleException {
		user.setRole(new String[] {Role.ROLE_ADMIN.toString()});
		User newUser = userService.register(user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getPassword(), user.getRole()[0]);
		return new ResponseEntity<>(newUser, OK);
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
