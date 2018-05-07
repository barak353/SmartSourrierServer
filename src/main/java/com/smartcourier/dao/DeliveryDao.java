package com.smartcourier.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartcourier.beans.Delivery;

@Repository
public interface DeliveryDao extends JpaRepository<Delivery, Long>{
	
}
