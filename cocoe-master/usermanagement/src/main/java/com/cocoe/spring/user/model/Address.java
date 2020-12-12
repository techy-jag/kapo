package com.cocoe.spring.user.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String dist;
	private String state;
	private Long pin;
	@Enumerated(EnumType.ORDINAL)
	private AddressType addresType;
}
