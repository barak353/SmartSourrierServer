package com.smartcourier.beans;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Delivery
 *
 */
@Entity
@Table(name="Delivery")

public class Delivery implements Serializable {

	@Id
	@GeneratedValue 
	private Long id;
	private String address;
	private String preferredArea;
	private String price;
	private String state;
	private String phone;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  	@PrimaryKeyJoinColumn
	private Agent agent;
	
	private static final long serialVersionUID = 1L;

	public Delivery() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}   
	public String getPreferredArea() {
		return this.preferredArea;
	}

	public void setPreferredArea(String preferredArea) {
		this.preferredArea = preferredArea;
	}   
	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}   
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}   
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
   
}
