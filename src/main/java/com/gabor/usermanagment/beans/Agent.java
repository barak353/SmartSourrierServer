package com.gabor.usermanagment.beans;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Agent
 *
 */
@Entity

public class Agent implements Serializable {

	   
	@Id
	private String did;
	private String email;
	private String phone;
	private String po;
	private ArrayList<Delivery> delivery;
	private static final long serialVersionUID = 1L;

	public Agent() {
		super();
	}   
	public String getDid() {
		return this.did;
	}

	public void setDid(String did) {
		this.did = did;
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
	public ArrayList<Delivery> getDelivery() {
		return this.delivery;
	}

	public void setDelivery(ArrayList<Delivery> delivery) {
		this.delivery = delivery;
	}
   
}
