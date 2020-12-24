package com.cocoe.spring.seller.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.cocoe.spring.seller.dto.ShopDetailsDTO;
import com.cocoe.spring.user.dto.mapper.AddressDTOToAddressMapper;
import com.cocoe.spring.user.model.Address;
import com.cocoe.spring.user.model.ShopDetails;

public class ShopDetailsDTOToShopDetailsMapper {
	
	public static List<ShopDetails> convertAll(List<ShopDetailsDTO> shops) {
		List<ShopDetails> 	shopDetails=new ArrayList<>();
		shops.forEach(shop->{
			shopDetails.add(convert(shop));
		});
		
		return shopDetails;
		
	}
	
	public static ShopDetails convert(ShopDetailsDTO shop) {
		ShopDetails shopDetails=new ShopDetails();
		shopDetails.setShopName(shop.getShopName());
		Address addr=AddressDTOToAddressMapper.convert(shop.getAddress());
		shopDetails.setAddress(addr);		
		return shopDetails;
		
	}

}
