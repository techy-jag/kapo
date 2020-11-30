package com.cocoe.spring.user.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.cocoe.spring.user.dto.UserDTO;
import com.cocoe.spring.user.model.User;

public class UserToUserDTOMapper {
	
	public static List<UserDTO> convertAll(List<User> users) {
		List<UserDTO> usersDTO=new ArrayList<UserDTO>();
		users.forEach(user->{
			usersDTO.add(convert(user));
		});
		
return usersDTO;
	
	}
	
public static UserDTO convert(User user) {
	UserDTO userDTO=new UserDTO();
	userDTO.setEmail(user.getEmail());
	userDTO.setFirstName(user.getFirstName());
	userDTO.setLastLoginDate(user.getLastLoginDate());
	userDTO.setLastName(user.getLastName());
	userDTO.setMobilenumber(user.getMobilenumber());
  List<String> roles=	user.getRoles().stream().
		  map(role->role.getName()).collect(Collectors.toList());
	userDTO.setRoles(roles);
	List<String> previleges=user.getRoles().stream().map(role->role.getPrivileges()).
	flatMap(pre->pre.stream()).map(prel->prel.getName()).collect(Collectors.toList());
	userDTO.setPrevileges(previleges);	
	return userDTO;
	
}
}
