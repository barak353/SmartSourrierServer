package com.smartcourier.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	private String preferredArea;
	private String po;
	private String totalPaid;

	
	public String getTotalPaid() {
		return totalPaid;
	}
	public void setTotalPaid(String totalPaid) {
		this.totalPaid = totalPaid;
	}

	@OneToOne(mappedBy = "agent", fetch = FetchType.LAZY)
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "agent_id")
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
	
	public String getPreferredArea() {
	    return preferredArea;
	}
	public void setPreferredArea(String preferredArea) {
	    this.preferredArea = preferredArea;
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
