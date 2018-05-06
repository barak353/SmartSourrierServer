package com.smartcourier.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartcourier.beans.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long>{
	
	
	
	
	
}
