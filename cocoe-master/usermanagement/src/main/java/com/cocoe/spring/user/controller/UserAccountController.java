package com.cocoe.spring.user.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cocoe.spring.user.dto.AddressDTO;
import com.cocoe.spring.user.dto.mapper.AddressDTOToAddressMapper;
import com.cocoe.spring.user.dto.mapper.AddressToAddressDTOMapper;
import com.cocoe.spring.user.model.Address;
import com.cocoe.spring.user.service.UserAccountDetailsService;

@RestController
@RequestMapping("/account")
public class UserAccountController {

	@Autowired
	private UserAccountDetailsService  userAccountDetailsService;
	
    @GetMapping("/getAllAddress")
    public ResponseEntity<Set<AddressDTO>> getAllAddress() {      	
    	Set<Address> addrs=userAccountDetailsService.getAllAddress();    
        return new ResponseEntity<>(AddressToAddressDTOMapper.convertAll(addrs),OK);
    }
    @GetMapping("/getAllDeliveryAddress")
    public ResponseEntity<Set<AddressDTO>> getAllDeliveryAddress() {      	
    	Set<Address> addrs=userAccountDetailsService.getAllDeliveryAddress();    
        return new ResponseEntity<>(AddressToAddressDTOMapper.convertAll(addrs),OK);
    }
    @GetMapping("/getAllBillingAddress")
    public ResponseEntity<Set<AddressDTO>> getAllBillingAddress() {      	
    	Set<Address> addrs=userAccountDetailsService.getAllDeliveryAddress();    
        return new ResponseEntity<>(AddressToAddressDTOMapper.convertAll(addrs),OK);
    }
    @PostMapping("/addProfileAddress")
    public ResponseEntity<AddressDTO> addProfileAddress(@RequestBody AddressDTO address) {      	
    	Address addr=userAccountDetailsService.addProfileAddress(AddressDTOToAddressMapper.convert(address));
        return new ResponseEntity<>(AddressToAddressDTOMapper.convert(addr),OK);
    }
    @PostMapping("/addShipping")
    public ResponseEntity<AddressDTO> addShipingAddress(@RequestBody AddressDTO address) {      	
    	Address addr=userAccountDetailsService.addShippingAddress(AddressDTOToAddressMapper.convert(address));
        return new ResponseEntity<>(AddressToAddressDTOMapper.convert(addr),OK);
    }
    @PostMapping("/addBilling")
    public ResponseEntity<AddressDTO> addBillingAddress(
    		@RequestBody AddressDTO address) {      	
    	Address addr=	userAccountDetailsService.addBillingAddress(AddressDTOToAddressMapper.convert(address));
        return new ResponseEntity<>(AddressToAddressDTOMapper.convert(addr),OK);
    }
    @PostMapping("/getProfileAddress")
    public ResponseEntity<AddressDTO> getProfileAddress(
    		@RequestBody AddressDTO address) {      	
    	Address addr=	userAccountDetailsService.getProfileAddress();
                return new ResponseEntity<>(AddressToAddressDTOMapper.convert(addr),OK);
    }
}
