package com.smartcourier.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcourier.beans.Customer;
import com.smartcourier.dao.CustomerDao;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/customerapi")
@Api(value="usermanagment")
public class customerAPI {

	public static final Logger logger = LoggerFactory.getLogger(customerAPI.class);

	@Autowired
	CustomerDao customerDao;

	@ApiOperation(value="View a list of customers", response= Iterable.class)
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Successfully received the list from database"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
			)

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
	}

}









