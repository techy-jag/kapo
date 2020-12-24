package com.cocoe.spring.seller.dto;
import com.cocoe.spring.user.dto.AddressDTO;

public class ShopDetailsDTO {
	private String shopName;
	private AddressDTO address;
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public AddressDTO getAddress() {
		return address;
	}
	public void setAddress(AddressDTO address) {
		this.address = address;
	}
}
