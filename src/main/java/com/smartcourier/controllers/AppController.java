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
@Api(value="App Management")
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
	
	@GetMapping("/user/getAll")
	public List<User> getAllUsers(){
		return appDao.findAll();
	}
	
	@ApiOperation(value="Create user", response= Iterable.class)
	@PostMapping("/user/create")
	public User createUser(@RequestBody User user) {
		if(appDao.findByUsername(user.getUsername()) == null){
			return appDao.save(user);
		} else{
			return null;
		}
	}
	
	@ApiOperation(value="Delete user", response= Iterable.class)
	@DeleteMapping("/user/delete/{username}")
	public Boolean deleteUser(@PathVariable(value = "username") String username) {
		User user = appDao.findByUsername(username);
		if(user != null){
			appDao.delete(user);
			return true;
		} else{
			return false;
		}
	}
	
	@ApiOperation(value="Update user", response= Iterable.class)
	@PutMapping("/user/update/{username}")
	public User updateUser(@PathVariable(value = "username") String username, @RequestBody User user) {
		User currentUser = appDao.findByUsername(username);
		if(currentUser != null){
			appDao.delete(currentUser);
			return appDao.save(user);
		} else{
			return null;
		}
	}
}









