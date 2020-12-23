package com.cocoe.spring.user.repository.dao;

import java.util.Set;

import com.cocoe.spring.user.model.Address;
import com.cocoe.spring.user.model.User;

public interface UserAccountDetailsDAO {
	Address addShippingAddress(User user,Address address) ;	
	Address addBillingAddress(User user,Address address) ;	
	Set<Address> findAllAddressForUser(User user);
	Set<Address> getBillingAddress(User user);
	Set<Address> getShippingAddress(User user);
	void addProfileAddress(User user, Address address);
}
