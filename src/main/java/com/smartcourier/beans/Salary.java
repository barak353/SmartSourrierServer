package com.smartcourier.beans;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Salary
 *
 */
@Entity
@Table(name="Salary")

public class Salary implements Serializable {

	@Id
	@GeneratedValue 
	private Long id;
	private String monthInYear;
	private String totalPaid;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  	@PrimaryKeyJoinColumn
	private Agent agent;
	
	private static final long serialVersionUID = 1L;
	
	public Salary() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMonthInYear() {
		return monthInYear;
	}

	public void setMonthInYear(String monthInYear) {
		this.monthInYear = monthInYear;
	}

	public String getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(String totalPaid) {
		this.totalPaid = totalPaid;
	}
   
}
