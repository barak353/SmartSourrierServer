package com.smartcourier.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.smartcourier.beans.Distribution;

@Repository
public interface DistributionDao extends JpaRepository<Distribution, Long>{
	
	
}
