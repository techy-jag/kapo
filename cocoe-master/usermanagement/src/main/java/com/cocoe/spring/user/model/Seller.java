package com.cocoe.spring.user.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Seller extends User{
	@OneToMany(mappedBy = "seller")	
	private Set<ShopDetails> shopDetails;

	public Seller() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seller(User user) {
		super(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), 
				user.getProfileImageUrl(), user.getLastLoginDateDisplay(), 
				user.getLastLoginDate(), user.getJoinDate(), user.getRoles(), user.getPassword(),
				user.getIsActive(), user.getIsNotLocked());
		
	}

	public Seller(Long id, String firstName, String lastName, String email) {
		super(id, firstName, lastName, email);
		
	}

	public Set<ShopDetails> getShopDetails() {
		return shopDetails;
	}

	public void setShopDetails(Set<ShopDetails> shopDetails) {
		this.shopDetails = shopDetails;
	}
	
	

}
