package com.cocoe.spring.seller.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.cocoe.spring.seller.dto.ShopDetailsDTO;
import com.cocoe.spring.user.dto.AddressDTO;
import com.cocoe.spring.user.dto.mapper.AddressToAddressDTOMapper;
import com.cocoe.spring.user.model.ShopDetails;

public class ShopDetailsToShopDetailsDTOMapper {
	
	public static List<ShopDetailsDTO> convertAll(List<ShopDetails> shops) {
		List<ShopDetailsDTO> 	shopDetails=new ArrayList<>();
		shops.forEach(shop->{
			shopDetails.add(convert(shop));
		});
		
		return shopDetails;
		
	}
	
	public static ShopDetailsDTO convert(ShopDetails shop) {
		ShopDetailsDTO shopDetails=new ShopDetailsDTO();
		shopDetails.setShopName(shop.getShopName());
		AddressDTO addr=AddressToAddressDTOMapper.convert(shop.getAddress());
		shopDetails.setAddress(addr);		
		return shopDetails;
		
	}

}
