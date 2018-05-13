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
import com.smartcourier.beans.User;
import com.smartcourier.dao.AgentDao;
import com.smartcourier.dao.AppDao;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/agent")
@Api(value="Agent Management")
public class AgentController {

	public static final Logger logger = LoggerFactory.getLogger(AgentController.class);

	@Autowired
	AppDao appDao;
	
	@Autowired
	AgentDao agentDao;

	@ApiOperation(value="Get agent", response= Iterable.class)
	@GetMapping("/{agentId}")
	public Agent getAgentById(@PathVariable(value = "agentId") Long agentId) {
		return agentDao.findOne(agentId);
	}
	
	@GetMapping("/getAll")
	public List<Agent> getAllAgents(){
		return agentDao.findAll();
	}
	
	@ApiOperation(value="Delete agent", response= Iterable.class)
	@DeleteMapping("/delete/{agentId}")
	public Boolean deleteAgent(@PathVariable(value = "agentId") Long agentId) {
		List<User> users = appDao.findAll();
		for(User user : users){
			if (user.getAgent() != null) {
				if(user.getAgent().getId().equals(agentId)){
					appDao.delete(user);
					return true;
				}
			}
		}
		return false;
	}
	
	/*@ApiOperation(value="Delete agent", response= Iterable.class)
	@DeleteMapping("/delete/{agentId}")
	public Boolean deleteAgent(@PathVariable(value = "agentId") Long agentId) {
		List<User> users = appController.getAllUsers();
		for(User user : users){
			if(user.getAgent().getId().equals(agentId)){
				break;
			}
		}
		Agent agent = agentDao.findOne(agentId);
		if(agent != null){
			agentDao.delete(agent);
			return true;
		} else{
			return false;
		}
	}*/
	
	@ApiOperation(value="Create agent", response= Iterable.class)
	@PostMapping("/create")
	public Agent createDelivery(@RequestBody Agent agent) {
		agentDao.save(agent);
		return agent;
	}
	
	@ApiOperation(value="Update agent", response= Iterable.class)
	@PutMapping("/update/{agentId}")
	public Agent updateAgent(@PathVariable(value = "agentId") Long agentId, @RequestBody Agent agent) {
		Agent currentAgent = agentDao.findOne(agentId);
		if(currentAgent != null){
			agentDao.delete(currentAgent);
			return agentDao.save(agent);
		} else{
			return null;
		}
	}
}









