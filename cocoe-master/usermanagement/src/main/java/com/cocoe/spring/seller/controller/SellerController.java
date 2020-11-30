package com.cocoe.spring.seller.controller;

import static com.cocoe.spring.user.constants.UserManagemnetConstants.EMAIL_SENT;
import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.cocoe.spring.user.service.UserDetailsService;

@RestController
@RequestMapping("/seller")
@PreAuthorize("hasAuthority('SELLER')")
public class SellerController {

	private static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully";
	@Autowired
	UserDetailsService userService;

	 public Authentication getAuthentication() {
	        return SecurityContextHolder.getContext().getAuthentication();
	    }

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
    
    @GetMapping("/find/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("email") String email) {
        User user = userService.getUserByEmail(email);
        return new ResponseEntity<>(UserToUserDTOMapper.convert(user), OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.getAllusers();
             return new ResponseEntity<>(UserToUserDTOMapper.convertAll(users), OK);
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
    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
