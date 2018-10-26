package com.smartcourier.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.smartcourier.beans.Division;

@Repository
public interface DivisionDao extends JpaRepository<Division, Long>{
	
	
}
