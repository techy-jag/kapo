package com.cocoe.spring.user.repository.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cocoe.spring.user.model.Address;
import com.cocoe.spring.user.model.User;
@Repository
@Transactional
public class UserAccountDetalsDAOImpl implements UserAccountDetailsDAO {
@PersistenceContext
	EntityManager em;	
	@Override
	public Set<Address> findAllAddressForUser(User user) {
		Query q=em.createQuery("Select addresses From User  u where u.id= "+user.getId());
		return new HashSet<Address>(q.getResultList()) ;
		
	}
	@Override
	public Address addBillingAddress(User user,Address address) {
		address.setUser(user);
		em.persist(address);
		return address;
	}
	
	@Override
	public Address addShippingAddress(User user,Address address) {
		address.setUser(user);
		em.persist(address);
		return address;
	}

	@Override	
	public Set<Address> getBillingAddress(User user) {
		Query q=em.createQuery("Select addr From Address  addr where addr.user.id= "+
                user.getId()+" and  addr.deliveryAddress =TRUE");
		return new HashSet<Address>(q.getResultList());
	}
	
	@Override
	public Set<Address> getShippingAddress(User user) {
		Query q=em.createQuery("Select addr From Address  addr where addr.user.id= "+
                user.getId()+" and  addr.billingAddress =TRUE");
		return new HashSet<Address>(q.getResultList());
	}
	@Override
	public void addProfileAddress(User sessionUser, Address address) {
		em.persist(address);
		User user=em.find(User.class, sessionUser.getId());
		user.setProfileAddress(address);		
		em.persist(user);
		
	}

}
