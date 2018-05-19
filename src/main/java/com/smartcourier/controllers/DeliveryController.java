package com.smartcourier.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smartcourier.beans.Agent;
import com.smartcourier.beans.Delivery;
import com.smartcourier.dao.AgentDao;
import com.smartcourier.dao.DeliveryDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/delivery")
@Api(value="Delivery Management")
public class DeliveryController {

	public static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);

	@Autowired
	DeliveryDao deliveryDao;
	
	@Autowired
	AgentDao agentDao;

	@ApiOperation(value="Get delivery", response= Iterable.class)
	@GetMapping("/{deliveryId}")
	public Delivery getDeliveryById(@PathVariable(value = "deliveryId") Long deliveryId) {
		return deliveryDao.findOne(deliveryId);
	}
	
	@GetMapping("/getAll")
	public List<Delivery> getAllDeliverys(){
		return deliveryDao.findAll();
	}
	
	@ApiOperation(value="Create delivery", response= Iterable.class)
	@PostMapping("/create/{agentId}")
	public Delivery createDelivery(@PathVariable(value = "agentId") Long agentId, @RequestBody Delivery delivery) {
		Agent agent = agentDao.findOne(agentId);
		if(agent != null){
			agentDao.delete(agent);
			agent.getDelivery().add(delivery);
			agentDao.save(agent);
			return delivery;
		} else{
			return null;
		}
	}
	
	@ApiOperation(value="Delete delivery", response= Iterable.class)
	@DeleteMapping("/delete/{agentId}/{deliveryId}")
	public Boolean deleteDelivery(@PathVariable(value = "agentId") Long agentId, @PathVariable(value = "deliveryId") Long deliveryId) {
		Agent agent = agentDao.findOne(agentId);
		if(agent != null){
			Delivery deliveryToDelete = null;
			for(Delivery delivery : agent.getDelivery()){
				if(delivery.getId().equals(deliveryId)){
					deliveryToDelete = delivery;
					break;
				}
			}
			if(deliveryToDelete == null)
				return false;//Agent dosen't have that delivery.
			agentDao.delete(agent);
			agent.getDelivery().remove(deliveryToDelete);
			agentDao.save(agent);
			deliveryDao.delete(deliveryToDelete);//We can delete delivery just after we detach the agent from that delivery.
			return true;
		} else{
			return null;
		}
	}
	
	@ApiOperation(value="Update delivery", response= Iterable.class)
	@PutMapping("/update/{deliveryId}")
	public Delivery updateDelivery(@PathVariable(value = "deliveryId") Long deliveryId, @RequestBody Delivery delivery) {
		Delivery currentDelivery = deliveryDao.findOne(deliveryId);
		if(currentDelivery != null){
			deliveryDao.delete(currentDelivery);
			return deliveryDao.save(delivery);
		} else{
			return null;//Agent is not exist.
		}
	}
}









