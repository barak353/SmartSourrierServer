package com.smartcourier.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Entity implementation class for Entity: Agent
 *
 */
@Entity

public class Agent implements Serializable {

	   
	@Id
	@GeneratedValue
	private Long id;
	
	private String email;
	private String phone;
	private String po;
	
	@OneToOne(mappedBy = "agent", fetch = FetchType.LAZY)
	private User user;
	
	@OneToMany(mappedBy="agent", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Delivery> delivery;
	
	private static final long serialVersionUID = 1L;

	public Agent() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}   
	public String getPo() {
		return this.po;
	}

	public void setPo(String po) {
		this.po = po;
	}   
	public List<Delivery> getDelivery() {
		return this.delivery;
	}

	public void setDelivery(List<Delivery> delivery) {
		this.delivery = delivery;
	}
   
}
