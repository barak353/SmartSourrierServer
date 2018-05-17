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

	/*@ApiOperation(value="Get salary", response= Iterable.class)
	@GetMapping("/{salaryId}")
	public Salary getSalaryById(@PathVariable(value = "salaryId") Long salaryId) {
		return salaryDao.findOne(salaryId);
	}
	
	@GetMapping("/getAll")
	public List<Salary> getAllSalarys(){
		return salaryDao.findAll();
	}*/
	
	/*@ApiOperation(value="Create salary", response= Iterable.class)
	@PostMapping("/create/{agentId}")
	public Salary createSalary(@PathVariable(value = "agentId") Long agentId, @RequestBody Salary salary) {
		return salary;
		/*Agent agent = agentDao.findOne(agentId);
		if(agent != null){
			agentDao.delete(agent);
			agent.getSalary().add(salary);
			agentDao.save(agent);
			return salary;
		} else{
			return null;
		}
	}
	*/
	/*@ApiOperation(value="Delete salary", response= Iterable.class)
	@DeleteMapping("/delete/{agentId}/{salaryId}")
	public Boolean deleteSalary(@PathVariable(value = "agentId") Long agentId, @PathVariable(value = "salaryId") Long salaryId) {
		Agent agent = agentDao.findOne(agentId);
		if(agent != null){
			Salary salaryToDelete = null;
			for(Salary salary : agent.getSalary()){
				if(salary.getId().equals(salaryId)){
					salaryToDelete = salary;
					break;
				}
			}
			if(salaryToDelete == null)
				return false;//Agent dosen't have that salary.
			agentDao.delete(agent);
			agent.getSalary().remove(salaryToDelete);
			agentDao.save(agent);
			salaryDao.delete(salaryToDelete);//We can delete salary just after we detach the agent from that salary.
			return true;
		} else{
			return null;
		}
	}
	
	@ApiOperation(value="Update salary", response= Iterable.class)
	@PutMapping("/update/{salaryId}")
	public Salary updateSalary(@PathVariable(value = "salaryId") Long salaryId, @RequestBody Salary salary) {
		Salary currentSalary = salaryDao.findOne(salaryId);
		if(currentSalary != null){
			salaryDao.delete(currentSalary);
			return salaryDao.save(salary);
		} else{
			return null;//Agent is not exist.
		}
	}*/
}









