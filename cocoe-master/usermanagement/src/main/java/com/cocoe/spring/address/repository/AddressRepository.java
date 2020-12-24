package com.cocoe.spring.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cocoe.spring.user.model.Address;


public interface AddressRepository extends JpaRepository<Address, Long>{

}
