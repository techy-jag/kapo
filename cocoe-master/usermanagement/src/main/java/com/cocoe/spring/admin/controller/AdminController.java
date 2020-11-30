package com.cocoe.spring.admin.controller;

import static com.cocoe.spring.user.constants.UserManagemnetConstants.EMAIL_SENT;
import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cocoe.spring.admin.dao.AdminReposiatory;
import com.cocoe.spring.ui.helper.UIControllerHelper;
import com.cocoe.spring.user.dto.UserDTO;
import com.cocoe.spring.user.dto.mapper.UserToUserDTOMapper;
import com.cocoe.spring.user.exception.EmailExistException;
import com.cocoe.spring.user.exception.EmailNotFoundException;
import com.cocoe.spring.user.exception.InvalidRoleException;
import com.cocoe.spring.user.exception.NotAnImageFileException;
import com.cocoe.spring.user.exception.RecordNotFoundException;
import com.cocoe.spring.user.exception.UserNotFoundException;
import com.cocoe.spring.user.exception.UsernameExistException;
import com.cocoe.spring.user.model.HttpResponse;
import com.cocoe.spring.user.model.User;
import com.cocoe.spring.user.repository.RoleRepository;
import com.cocoe.spring.user.service.UserDetailsService;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

	private static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully";
	@Autowired
	private UserDetailsService userService;
	@Autowired
	private UIControllerHelper uiControllerHelper;
	@Autowired
	private AdminReposiatory  adminReposiatory;
	@Autowired
	private RoleRepository roleRepository;
    @PostMapping("/update")
    public ResponseEntity<UserDTO> update(@RequestParam("currentEmail") String currentEmail,
                                       @RequestParam("firstName") String firstName,
                                       @RequestParam("lastName") String lastName,                                      
                                       @RequestParam("email") String email,
                                       @RequestParam("role") String role
                                      ) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException, InvalidRoleException {
        User updatedUser = userService.updateUser(currentEmail, firstName, lastName,email, role);
    	return new ResponseEntity<>(UserToUserDTOMapper.convert(updatedUser), OK);
    }
    @GetMapping("/resetpassword/{email}")
    public ResponseEntity<HttpResponse> resetPassword(@PathVariable("email") String email) throws MessagingException, EmailNotFoundException {
        userService.resetPassword(email);
        return response(OK, EMAIL_SENT + email);
    }
    @DeleteMapping("/delete/{email}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public ResponseEntity<HttpResponse> deleteUser(@PathVariable("username") String email) throws RecordNotFoundException {
        userService.deleteuserByEmail(email);
        return response(OK, USER_DELETED_SUCCESSFULLY);
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.getAllusers();  
	return new ResponseEntity<>(UserToUserDTOMapper.convertAll(users), OK);
    }
    
    @GetMapping("/allCustomer")
    public ResponseEntity<List<UserDTO>> getAllCustomer() {
        List<User> users = adminReposiatory.getAllUsers(Arrays.asList("ROLE_USER"));
    	return new ResponseEntity<>(UserToUserDTOMapper.convertAll(users), OK);
    }
    @GetMapping("/allSeller")
    public ResponseEntity<List<UserDTO>> getAllSeller() {
        List<User> users = adminReposiatory.getAllUsers(Arrays.asList("ROLE_SELLER"));
        return new ResponseEntity<>(UserToUserDTOMapper.convertAll(users), OK);
    }
    
    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
    
    
    
    
    
    
}
