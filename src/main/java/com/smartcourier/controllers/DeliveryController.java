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

import com.smartcourier.ABCalgorithm;
import com.smartcourier.beans.Courier;
import com.smartcourier.beans.Delivery;
import com.smartcourier.dao.CourierDao;
import com.smartcourier.dao.DeliveryDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/*חשוב!
 * אני צריך טבלה של הכתובות של כל המפעלים הכתובות זה שני מספרים של
 *  longitudeוש altitude 
 *  שהם משתני 
 *  float.
 *  נשאר לי לקחת כתובת של משלוח ולשרשר לסטרינג ואז להשתמש 
 * google maps api ב
 * שאני שולח כתובת ואני מקבל בחזרה
 *  longitude 
 *  ו altitude
 *   נשאר לי לחשב מרחק בין שני נקודות וזה הפרמטר של המרחק של המשלוח מהמפעל.
 *   עומר הביא לי קוד שעושה את ההמרה הזאת בעזרת גוגל מאפס
 */

@RestController
@RequestMapping(path = "/delivery")
@Api(value="Delivery Management")
public class DeliveryController {

	public static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);

	@Autowired
	DeliveryDao deliveryDao;
	
	@Autowired
	CourierDao courierDao;

	private ABCalgorithm beeColony = new ABCalgorithm();	
	
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
	@PostMapping("/create/{courierId}")
	public Delivery createDeliveryAndAssignToCourier(@PathVariable(value = "courierId") Long courierId, @RequestBody Delivery delivery) {
		Courier courier = courierDao.findOne(courierId);
		if(courier != null){
			courierDao.delete(courier);
			courier.getDelivery().add(delivery);
			courierDao.save(courier);
			return delivery;
		} else{
			return null;
		}
	}
	
	@ApiOperation(value="Create delivery", response= Iterable.class)
	@PostMapping("/create")
	public Delivery createDelivery(@RequestBody Delivery delivery) {
		deliveryDao.save(delivery);
		beeColony.runABCalgorithm();
		return delivery;
	}
	
	@ApiOperation(value="Delete delivery", response= Iterable.class)
	@DeleteMapping("/delete/{courierId}/{deliveryId}")
	public Boolean deleteDelivery(@PathVariable(value = "courierId") Long courierId, @PathVariable(value = "deliveryId") Long deliveryId) {
		Courier courier = courierDao.findOne(courierId);
		if(courier != null){
			Delivery deliveryToDelete = null;
			for(Delivery delivery : courier.getDelivery()){
				if(delivery.getId().equals(deliveryId)){
					deliveryToDelete = delivery;
					break;
				}
			}
			if(deliveryToDelete == null)
				return false;//Courier dosen't have that delivery.
			courierDao.delete(courier);
			courier.getDelivery().remove(deliveryToDelete);
			courierDao.save(courier);
			deliveryDao.delete(deliveryToDelete);//We can delete delivery just after we detach the courier from that delivery.
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
			return null;//Courier is not exist.
		}
	}
}









