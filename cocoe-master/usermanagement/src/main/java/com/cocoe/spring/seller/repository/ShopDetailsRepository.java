package com.cocoe.spring.seller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cocoe.spring.user.model.Seller;
import com.cocoe.spring.user.model.ShopDetails;

public interface ShopDetailsRepository extends JpaRepository<ShopDetails, Long> {
  @Query("Select  s from ShopDetails s where s.seller.id= :seller")
	List<ShopDetails>  getAllShopDetails(@Param(value = "seller") Long seller);
;}
