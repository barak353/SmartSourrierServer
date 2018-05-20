package com.smartcourier.controllers;

import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smartcourier.beans.Agent;
import com.smartcourier.beans.Delivery;
import com.smartcourier.beans.Salary;
import com.smartcourier.dao.AgentDao;
import com.smartcourier.dao.SalaryDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/salary")
@Api(value="Salary Management")
public class SalaryController {

	public static final Logger logger = LoggerFactory.getLogger(SalaryController.class);

	@Autowired
	SalaryDao salaryDao;
	
	@Autowired
	AgentDao agentDao;

	@GetMapping("/getAll")
	public List<Salary> getAllSalary(){
		return salaryDao.findAll();
	}
	
	@ApiOperation(value="Create salary", response= Iterable.class)
	@PostMapping("/create/{agentId}")
	public Salary createSalary(@PathVariable(value = "agentId") Long agentId, @RequestBody Salary salary) {
		Agent agent = agentDao.findOne(agentId);
		if(agent != null){
			for (Iterator<Salary> iterator = agent.getSalary().iterator(); iterator.hasNext();) {
			    Salary salaryIt = iterator.next();
			    if (salaryIt.getMonthInYear().equals(salary.getMonthInYear())) {//if monthInYear already exist in agent's salaries list.
			        // Remove the current element from the iterator and the list.
			        iterator.remove();
					salaryDao.delete(salaryIt);
			    }
			}
			agentDao.delete(agent);
			agent.getSalary().add(salary);
			agentDao.save(agent);
			
			return salary;
		} else{
			return null;
		}
	}
	
	@ApiOperation(value="Get delivery", response= Iterable.class)
	@GetMapping("/getByMonthInYear/{monthInYear}")
	public List<Salary> getSalaryByMonthInYear(@PathVariable(value = "monthInYear") String monthInYear) {
		List<Salary> salaries = salaryDao.findByMonthInYear(monthInYear);
		return salaries;
	}
	
	
/*	@ApiOperation(value="Update salary", response= Iterable.class)
	@PutMapping("/update/{salaryId}")
	public Delivery updateDelivery(@PathVariable(value = "deliveryId") Long salaryId, @RequestBody Salary salary) {
		Delivery currentDelivery = salaryDao.findOne(salaryId);
		if(currentDelivery != null){
			deliveryDao.delete(currentDelivery);
			return deliveryDao.save(delivery);
		} else{
			return null;//Agent is not exist.
		}
	}*/
}









