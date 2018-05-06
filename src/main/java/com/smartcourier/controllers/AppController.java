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

import com.smartcourier.beans.User;
import com.smartcourier.dao.AppDao;
import com.smartcourier.model.LoginIn;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/app")
@Api(value="usermanagment")
public class AppController {

	public static final Logger logger = LoggerFactory.getLogger(AppController.class);

	@Autowired
	AppDao appDao;

	@ApiOperation(value="login", response= Iterable.class)
	@PostMapping("/login")
	public Boolean login(@RequestBody LoginIn loginIn) {
		User user= appDao.findByUsername(loginIn.getUsername());
		if(user == null) {
			return false;
		}
		
		if(!user.getPassword().equals(loginIn.getPassword())) {
			return false;
		}
		
		return true;
	}
	
	@ApiOperation(value="Get user", response= Iterable.class)
	@GetMapping("/user/{username}")
	public User getUser(@PathVariable(value = "username") String username) {
		return appDao.findByUsername(username);
	}
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers(){
		return appDao.findAll();
	}
	
	@ApiOperation(value="Create user", response= Iterable.class)
	@PostMapping("/createuser")
	public User createUser(@RequestBody User user) {
		if(appDao.findByUsername(user.getUsername()) == null){
			return appDao.save(user);
		} else{
			return null;
		}
	}
	
	@ApiOperation(value="Delete user", response= Iterable.class)
	@DeleteMapping("/deleteuser")
	public User deleteUser(@RequestBody User user) {
		return appDao.save(user);
	}
	
	@ApiOperation(value="Update user", response= Iterable.class)
	@PutMapping("/updateuser")
	public User updateUser(@RequestBody User user) {
		return appDao.save(user);
	}
	
	
	/*
	

	@GetMapping("/getAll")
	public List<Customer> getAllCustomers(){
		return customerDao.findAll();
	}
	
	@ApiOperation(value="Creating a user", response= Iterable.class)
	@PostMapping("/createCustomer")
	public Customer createCustomer(@Valid @RequestBody Customer customer) {
		return customerDao.save(customer);
	}
	
	@ApiOperation(value="Getting customer by ID", response= Iterable.class)
	@GetMapping("/getCustomerbyID/{customerID}")
	public ResponseEntity<Customer> getNoteById(@PathVariable(value = "customerID") Long customerID) {
		Customer customer = customerDao.findOne(customerID);
		if(customer == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(customer);
	}
	
	@ApiOperation(value="updating customer by ID", response= Iterable.class)
	@PutMapping("/updateCustomer/{customerID}")
	public ResponseEntity<Customer> updateNote(@PathVariable(value = "customerID") Long customerID, 
			@Valid @RequestBody Customer customerdetails) {
		Customer customer = customerDao.findOne(customerID);
		if(customer == null) {
			return ResponseEntity.notFound().build();
		}
		customer.setCustomerAddress(customerdetails.getCustomerAddress());
		customer.setCustomerName(customerdetails.getCustomerName());
		customer.setCustomerPhoneNumber(customerdetails.getCustomerPhoneNumber());

		Customer updatedNote = customerDao.save(customer);
		return ResponseEntity.ok(updatedNote);
	}
	
	@ApiOperation(value="Deleting Customer by ID", response= Iterable.class)
	@DeleteMapping("/deleteCustomer/{customerID}")
	public ResponseEntity<Customer> deleteNote(@PathVariable(value = "customerID") Long customerID) {
		Customer customer = customerDao.findOne(customerID);
		if(customer == null) {
			return ResponseEntity.notFound().build();
		}

		customerDao.delete(customerID);
		return ResponseEntity.ok().build();
	}*/

}









