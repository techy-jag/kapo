package com.cocoe.spring.user.dto.mapper;

import java.util.HashSet;
import java.util.Set;

import com.cocoe.spring.user.dto.AddressDTO;
import com.cocoe.spring.user.model.Address;


public class AddressDTOToAddressMapper {
	
	public static Set<Address> convertAll(Set<AddressDTO> addrs) {
		Set<Address> addressDTO=new HashSet<>();
		addrs.forEach(addr->{
			addressDTO.add(convert(addr));
		});
		
return addressDTO;
	
	}
	
public static Address convert(AddressDTO address) {
	Address addressDTO=new Address();
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
