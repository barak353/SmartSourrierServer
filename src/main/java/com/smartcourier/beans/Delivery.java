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
@UniqueConstraint(columnNames={"courier_id", "monthInYear"})
)

public class Delivery implements Serializable {

	@Id
	@GeneratedValue 
	//DB's and algorithm's details
	private Long id;
	private Integer type;//There are 4 types od deliveries: Type0, Type1, Type 2 and Type 3 as described in section 3.6.
	private Double lat;
	private Double lng;
	private String subarea;
	private String address;
	private String area;
	private String urgent;
	//Delivery's details
	private String claimant;
	private String name;
	private String phone;
	private String box;
	private String duedate;
	private String date;	
	private String not_found;
	private String reveiwer_name;
	private String floor;
	private String entrance;
	private String num_of_floor;
	private String private_house;
	private String signed;
	private String pasted_on_door;
	private String text;
	//Salaray's details
	private String monthInYear; 
	private String price;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  	@PrimaryKeyJoinColumn
	private Courier courier;
	
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
		return monthInYear;
	}
	public void setMonthInYear(String monthInYear) {
		this.monthInYear = monthInYear;
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

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public String getSubarea() {
		return subarea;
	}
	public void setSubarea(String subarea) {
		this.subarea = subarea;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getUrgent() {
		return urgent;
	}
	public void setUrgent(String urgent) {
		this.urgent = urgent;
	}
	public String getClaimant() {
		return claimant;
	}
	public void setClaimant(String claimant) {
		this.claimant = claimant;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBox() {
		return box;
	}
	public void setBox(String box) {
		this.box = box;
	}
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNot_found() {
		return not_found;
	}
	public void setNot_found(String not_found) {
		this.not_found = not_found;
	}
	public String getReveiwer_name() {
		return reveiwer_name;
	}
	public void setReveiwer_name(String reveiwer_name) {
		this.reveiwer_name = reveiwer_name;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getEntrance() {
		return entrance;
	}
	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}
	public String getNum_of_floor() {
		return num_of_floor;
	}
	public void setNum_of_floor(String num_of_floor) {
		this.num_of_floor = num_of_floor;
	}
	public String getPrivate_house() {
		return private_house;
	}
	public void setPrivate_house(String private_house) {
		this.private_house = private_house;
	}
	public String getSigned() {
		return signed;
	}
	public void setSigned(String signed) {
		this.signed = signed;
	}
	public String getPasted_on_door() {
		return pasted_on_door;
	}
	public void setPasted_on_door(String pasted_on_door) {
		this.pasted_on_door = pasted_on_door;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Courier getCourier() {
		return courier;
	}
	public void setCourier(Courier courier) {
		this.courier = courier;
	}
   
}
