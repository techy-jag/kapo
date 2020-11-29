package com.cocoe.spring.admin.controller;

import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cocoe.spring.admin.dao.AdminReposiatory;
import com.cocoe.spring.ui.helper.UIControllerHelper;
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
import static com.cocoe.spring.user.constants.UserManagemnetConstants.*;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('admin:read')")
public class AdminController {

	private static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully";
	@Autowired
	private UserDetailsService userService;
	@Autowired
	private UIControllerHelper uiControllerHelper;
	@Autowired
	private AdminReposiatory  adminReposiatory;
    @PostMapping("/update")
    public ResponseEntity<User> update(@RequestParam("currentEmail") String currentEmail,
                                       @RequestParam("firstName") String firstName,
                                       @RequestParam("lastName") String lastName,                                      
                                       @RequestParam("email") String email,
                                       @RequestParam("role") String role
                                      ) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException, InvalidRoleException {
        User updatedUser = userService.updateUser(currentEmail, firstName, lastName,email, role);
        return new ResponseEntity<>(updatedUser, OK);
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
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllusers();
   System.out.println(uiControllerHelper.getAuthentication().getAuthorities());
         return new ResponseEntity<>(users, OK);
    }
    
    @GetMapping("/allCustomer")
    public ResponseEntity<List<User>> getAllCustomer() {
        List<User> users = adminReposiatory.getAllUsers("ROLE_USER");
        return new ResponseEntity<>(users, OK);
    }
    @GetMapping("/allSeller")
    public ResponseEntity<List<User>> getAllSeller() {
        List<User> users = adminReposiatory.getAllUsers("ROLE_SELLER");
        return new ResponseEntity<>(users, OK);
    }
    
    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
    
    
    
    
    
    
}
