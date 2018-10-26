package com.smartcourier.beans;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Division
 *
 */
@Entity
@Table(name="Division", 
uniqueConstraints=
@UniqueConstraint(columnNames={"id"})
)
public class Division implements Serializable {

	@Id
	@GeneratedValue 
	//DB's and algorithm's details
	private Long id;

	private static final long serialVersionUID = 1L;

	public Division() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
