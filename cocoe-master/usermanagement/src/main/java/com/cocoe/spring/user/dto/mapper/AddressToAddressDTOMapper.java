package com.cocoe.spring.user.dto.mapper;

import java.util.HashSet;
import java.util.Set;

import com.cocoe.spring.user.dto.AddressDTO;
import com.cocoe.spring.user.model.Address;


public class AddressToAddressDTOMapper {
	
	public static Set<AddressDTO> convertAll(Set<Address> addrs) {
		Set<AddressDTO> addressDTO=new HashSet<>();
		addrs.forEach(addr->{
			addressDTO.add(convert(addr));
		});
		
return addressDTO;
	
	}
	
public static AddressDTO convert(Address address) {
	AddressDTO addressDTO=new AddressDTO();
	addressDTO.setBillingAddress(address.isBillingAddress());
	addressDTO.setCity(address.getCity());
	addressDTO.setCountry(address.getCountry());
	addressDTO.setDeliveryAddress(address.isDeliveryAddress());
	addressDTO.setFirstName(address.getFirstName());
	addressDTO.setId(address.getId());
	addressDTO.setLandmark(address.getLandmark());
	addressDTO.setLastName(address.getLastName());
	addressDTO.setLine1(address.getLine1());
	addressDTO.setLine2(address.getLine2());
	addressDTO.setState(address.getState());
	return addressDTO;
	
}
}
