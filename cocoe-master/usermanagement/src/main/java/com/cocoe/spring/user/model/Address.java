package com.cocoe.spring.user.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.cocoe.spring.user.AddressType;

@Entity
public class Address {
	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	private String line1;
	private String line2;
	private String landmark;
	private String city;
	private String State;
	private String country;
	private boolean deliveryAddress;
	private boolean billingAddress;
	public boolean isDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(boolean deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public boolean isBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(boolean billingAddress) {
		this.billingAddress = billingAddress;
	}
	@ManyToOne
	private User user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
	

}
