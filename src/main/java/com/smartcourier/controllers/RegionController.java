package com.smartcourier.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smartcourier.beans.Region;
import com.smartcourier.dao.RegionDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/region")
@Api(value="Region Management")
public class RegionController {

	public static final Logger logger = LoggerFactory.getLogger(RegionController.class);

	@Autowired
	RegionDao regionDao;
	
	@GetMapping("/getAll")
	public List<Region> getAllSalary(){
		return regionDao.findAll();
	}
	
	@ApiOperation(value="Create region", response= Iterable.class)
	@PutMapping("/create")
	public Region createRegion(@RequestBody Region region) {
		regionDao.save(region);
		return region;
	}

	//Needs to add option for deleting region. If we delete region we need to iterate all the deliveries in this region and delete this region. Then we need to change those deliveries to type 0.
}





