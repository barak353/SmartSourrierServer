package com.gabor.usermanagment.beans;

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
	private String did;
	private String address;
	private String preferredArea;
	private String price;
	private String state;
	private String phone;
	private static final long serialVersionUID = 1L;

	public Delivery() {
		super();
	}   
	public String getDid() {
		return this.did;
	}

	public void setDid(String did) {
		this.did = did;
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
