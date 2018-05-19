package com.smartcourier.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smartcourier.beans.Salary;
import com.smartcourier.dao.AgentDao;
import com.smartcourier.dao.SalaryDao;
import io.swagger.annotations.Api;

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
	
}









