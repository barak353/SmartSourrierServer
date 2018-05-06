package com.smartcourier.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartcourier.beans.Agent;
import com.smartcourier.beans.User;

@Repository
public interface AgentDao extends JpaRepository<Agent, Long>{
	
}
