package com.cocoe.spring.seller.service;

import java.util.List;

import com.cocoe.spring.user.model.ShopDetails;



public interface ShopDetailsService {
	
	void addShopDetails(ShopDetails shopDetails);
	List<ShopDetails> getSellers();

}
