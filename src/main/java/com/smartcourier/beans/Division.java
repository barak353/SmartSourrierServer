package com.smartcourier.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Division
 *
 */
@Entity
@Table(name="Division", 
uniqueConstraints=
@UniqueConstraint(columnNames={"division_id"})
)
public class Division implements Serializable {

	@Id
	@GeneratedValue 
	//DB's and algorithm's details
	private Long division_id;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "division_id")
	private List<Delivery> delivery;
	
	public Long getDivision_id() {
		return division_id;
	}

	public void setDivision_id(Long division_id) {
		this.division_id = division_id;
	}

	public List<Delivery> getDelivery() {
		return delivery;
	}

	public void setDelivery(List<Delivery> delivery) {
		this.delivery = delivery;
	}

	public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	@OneToOne(mappedBy = "division", fetch = FetchType.LAZY)
	private Courier courier;
	
	private static final long serialVersionUID = 1L;

	public Division() {
		super();
	}
	
	public Long getId() {
		return division_id;
	}

	public void setId(Long division_id) {
		this.division_id = division_id;
	}

}
