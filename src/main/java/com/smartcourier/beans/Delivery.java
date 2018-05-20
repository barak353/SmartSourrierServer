package com.smartcourier.beans;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Delivery
 *
 */
@Entity
@Table(name="Delivery", 
uniqueConstraints=
@UniqueConstraint(columnNames={"agent_id", "monthInYear"})
)

public class Delivery implements Serializable {

	@Id
	@GeneratedValue 
	private Long id;
	private String MonthInYear;
	private String address;
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
	
	public String getMonthInYear() {
		return MonthInYear;
	}
	public void setMonthInYear(String monthInYear) {
		MonthInYear = monthInYear;
	}
	
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
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
