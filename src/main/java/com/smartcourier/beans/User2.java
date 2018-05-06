package com.smartcourier.beans;

import com.smartcourier.beans.Agent;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */

public class User2 {

	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	
	private String password;
	
/*	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "agent_id")
	private Agent agent;
	private static final long serialVersionUID = 1L;
*/
	public User2() {
		super();
	}   
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	/*public Agent getAgent() {
		return this.agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}*/
   
}
