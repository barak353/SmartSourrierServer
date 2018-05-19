package com.smartcourier.beans;

import java.io.Serializable;
import javax.persistence.*;

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
	private Long idAgent;


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
	
	public String getIdAgent() {
		//idAgent = agent.getId().toString();//idAgent as public getter so it will appear in the salary's json file that will be sent to the client.
		//In this way we don't need to generate getter for the agent object, so the agent object will not appear in the json file and in that way we eliminate infinity loop in the json file and eliminate bean exception.
		return idAgent.toString();
	}

	public void setIdAgent(Long agentId) {
		this.idAgent = agentId;
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
