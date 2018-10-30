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

/**
 * Entity implementation class for Entity: Courier
 *
 */
@Entity

public class Courier implements Serializable {

	   
	@Id
	@GeneratedValue
	private Long courier_id;
	private String email;
	private String phone;
	private String preferredArea;
	public List<Delivery> getDelivery() {
		return delivery;
	}

	public void setDelivery(List<Delivery> delivery) {
		this.delivery = delivery;
	}

	private String po;

	@OneToOne(mappedBy = "courier", fetch = FetchType.LAZY)
	private User user;
	
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id")
	private List<Salary> salary;*/
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "courier_id")
	private List<Delivery> delivery;
	
	private static final long serialVersionUID = 1L;

	public Long getCourier_id() {
		return courier_id;
	}

	public void setCourier_id(Long courier_id) {
		this.courier_id = courier_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Courier() {
		super();
	}  
	 
	public Long getId() {
		return this.courier_id;
	}

	public void setId(Long courier_id) {
		this.courier_id = courier_id;
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
	
	/*public List<Salary> getSalary() {
		return salary;
	}
	
	public void setSalary(List<Salary> salary) {
		this.salary = salary;
	}*/
}
