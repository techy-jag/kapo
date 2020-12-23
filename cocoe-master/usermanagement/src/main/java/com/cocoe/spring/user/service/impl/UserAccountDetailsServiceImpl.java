package com.cocoe.spring.user.service.impl;

import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cocoe.spring.user.model.Address;
import com.cocoe.spring.user.model.User;
import com.cocoe.spring.user.repository.dao.UserAccountDetailsDAO;
import com.cocoe.spring.user.service.UserAccountDetailsService;
import com.cocoe.spring.user.service.UserDetailsService;
@Service
public class UserAccountDetailsServiceImpl implements UserAccountDetailsService {
  @Autowired
	private UserDetailsService  userDetailsService;
	@Autowired
	private UserAccountDetailsDAO userAccountDetailsDAO;

	@Override
	public Set<Address> getAllAddress() {		
		return userAccountDetailsDAO.findAllAddressForUser(
				userDetailsService.getCurrentUser());	
	}
	@Override
		public Set<Address> getAllBillingAddress() {		
		return userAccountDetailsDAO.getBillingAddress(
				userDetailsService.getCurrentUser());	
	}
	@Override
		public Set<Address> getAllDeliveryAddress() {		
		return userAccountDetailsDAO.getShippingAddress(
				userDetailsService.getCurrentUser());	
	}
	@Override
	public Address addShippingAddress(Address address) {		
	User user=	userDetailsService.getCurrentUser();
	address.setDeliveryAddress(true);;
	userAccountDetailsDAO.addShippingAddress(user, address);
		return address;
	}
	@Override
	public Address addProfileAddress(Address address) {		
	User user=	userDetailsService.getCurrentUser();
	userAccountDetailsDAO.addProfileAddress(user, address);
		return address;
	}

	@Override
	public Address addBillingAddress(Address address) {
		User user=	userDetailsService.getCurrentUser();
		address.setBillingAddress(true);
		userAccountDetailsDAO.addBillingAddress(user, address);
		return address;
	}
	@Override
	public Address getProfileAddress() {		
		return userDetailsService.getCurrentUser().getProfileAddress();
	}
	
	

}
