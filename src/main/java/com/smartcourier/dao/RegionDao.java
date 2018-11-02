package com.smartcourier.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smartcourier.beans.Delivery;
import com.smartcourier.beans.Region;

@Repository
public interface RegionDao extends JpaRepository<Region, Long>{
	public Region findByRegionName(String regionName);
}
