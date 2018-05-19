package com.smartcourier.beans;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Entity implementation class for Entity: Salary
 *
 */
@Entity
@Table(
        name="SALARY", 
        uniqueConstraints=
            @UniqueConstraint(columnNames={"agent_id", "monthInYear"})
    )


public class Salary implements Serializable {

	@Id
	@GeneratedValue 
	private Long id;
	private String monthInYear;
	private String totalPaid;


	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  	@PrimaryKeyJoinColumn
  	@JoinColumn(name = "agent_id")
	private Agent agent;
	
	public String getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(String totalPaid) {
		this.totalPaid = totalPaid;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

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

   
}
