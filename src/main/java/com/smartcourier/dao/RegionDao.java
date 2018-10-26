package com.smartcourier.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.smartcourier.beans.Region;

@Repository
public interface RegionDao extends JpaRepository<Region, Long>{
	public Region findByRegion(String region);
}
