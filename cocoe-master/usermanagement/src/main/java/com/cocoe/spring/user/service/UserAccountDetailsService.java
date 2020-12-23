package com.cocoe.spring.user.service;

import java.util.Set;

import com.cocoe.spring.user.model.Address;

public interface UserAccountDetailsService {
	Address addShippingAddress(Address address) ;	
	Address addBillingAddress(Address address) ;
	Set<Address> getAllAddress();
	Set<Address> getAllBillingAddress();
	Set<Address> getAllDeliveryAddress();
	Address addProfileAddress(Address address);
	Address getProfileAddress();

}
