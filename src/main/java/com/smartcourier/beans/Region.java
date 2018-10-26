package com.smartcourier.beans;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Region
 *
 */
@Entity
@Table(name="Region", 
uniqueConstraints=
@UniqueConstraint(columnNames={"id"})
)
public class Region implements Serializable {

	@Id
	@GeneratedValue 
	//DB's and algorithm's details
	private Long id;

	private static final long serialVersionUID = 1L;

	public Region() {
		super();
	}
   
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
