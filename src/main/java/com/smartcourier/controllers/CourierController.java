package com.smartcourier.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcourier.beans.Courier;
import com.smartcourier.beans.User;
import com.smartcourier.dao.CourierDao;
import com.smartcourier.dao.AppDao;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/courier")
@Api(value="Courier Management")
public class CourierController {

	public static final Logger logger = LoggerFactory.getLogger(CourierController.class);

	@Autowired
	AppDao appDao;
	
	@Autowired
	CourierDao courierDao;

	@ApiOperation(value="Get courier", response= Iterable.class)
	@GetMapping("/{courierId}")
	public Courier getCourierById(@PathVariable(value = "courierId") Long courierId) {
		return courierDao.findOne(courierId);
	}
	
	@GetMapping("/getAll")
	public List<Courier> getAllCouriers(){
		return courierDao.findAll();
	}
	
	@ApiOperation(value="Delete courier", response= Iterable.class)
	@DeleteMapping("/delete/{courierId}")
	public Boolean deleteCourier(@PathVariable(value = "courierId") Long courierId) {
		List<User> users = appDao.findAll();
		for(User user : users){
            if (user.getCourier() != null) {
                if(user.getCourier().getId().equals(courierId)){
                    appDao.delete(user);
                    return true;
                }
			}
		}
		return false;
	}
	
	/*@ApiOperation(value="Delete courier", response= Iterable.class)
	@DeleteMapping("/delete/{courierId}")
	public Boolean deleteCourier(@PathVariable(value = "courierId") Long courierId) {
		List<User> users = appController.getAllUsers();
		for(User user : users){
			if(user.getCourier().getId().equals(courierId)){
				break;
			}
		}
		Courier courier = courierDao.findOne(courierId);
		if(courier != null){
			courierDao.delete(courier);
			return true;
		} else{
			return false;
		}
	}*/
	
	@ApiOperation(value="Create courier", response= Iterable.class)
	@PutMapping("/create")
	public Courier createDelivery(@RequestBody Courier courier) {
		courierDao.save(courier);
		return courier;
	}
	
	@ApiOperation(value="Update courier", response= Iterable.class)
	@PutMapping("/update/{courierId}")
	public Courier updateCourier(@PathVariable(value = "courierId") Long courierId, @RequestBody Courier courier) {
		Courier currentCourier = courierDao.findOne(courierId);
		if(currentCourier != null){
			courierDao.delete(currentCourier);
			return courierDao.save(courier);
		} else{
			return null;
		}
	}
}
