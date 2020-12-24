package com.cocoe.spring.seller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cocoe.spring.address.repository.AddressRepository;
import com.cocoe.spring.seller.repository.ShopDetailsRepository;
import com.cocoe.spring.user.model.Address;
import com.cocoe.spring.user.model.Seller;
import com.cocoe.spring.user.model.ShopDetails;
import com.cocoe.spring.user.service.UserDetailsService;
@Service
public class ShopDetailsServiceImpl implements ShopDetailsService {
	@Autowired
	private ShopDetailsRepository shopDetailsRepository;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public void addShopDetails(ShopDetails shopDetails) {
		shopDetails.setSeller((Seller)userDetailsService.getCurrentUser());
		Address addr=addressRepository.save(shopDetails.getAddress());
		shopDetails.setAddress(addr);
		shopDetailsRepository.save(shopDetails);		
	}

	@Override
	public List<ShopDetails> getSellers() {
		return shopDetailsRepository.getAllShopDetails(
				userDetailsService.getCurrentUser().getId());
	}


}
