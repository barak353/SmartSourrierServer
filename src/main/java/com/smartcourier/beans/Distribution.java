package com.smartcourier.beans;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Distribution
 *
 */
@Entity
@Table(name="Distribution", 
uniqueConstraints=
@UniqueConstraint(columnNames={"id"})
)
public class Distribution implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue 
	//DB's and algorithm's details
	private Long id;
	@ElementCollection
	private Map<String, Double> factors;
	
	public Distribution() {
		super();
	}
   
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<String, Double> getFactors() {
		return factors;
	}

	public void setFactors(Map<String, Double> factors) {
		this.factors = factors;
	}
	
}
