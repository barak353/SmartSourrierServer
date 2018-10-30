package com.smartcourier.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Region
 *
 */
@Entity
@Table(name="Region", 
uniqueConstraints=
@UniqueConstraint(columnNames={"region_id"})
)
public class Region implements Serializable {

	@Id
	@GeneratedValue 
	//DB's and algorithm's details
	private Long region_id;
	private String regionName;
	private Integer threshold;//Inside createDelivery method in DeliveryController class, the threshold of the region of the created delivery will be compared with the number of the deliveries in that region. f the threshold has been exceeded, then the distribution algorithm will be called on that region.
	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
	private List<Delivery> delivery;

	@ManyToMany(cascade = { 
		    CascadeType.PERSIST, 
		    CascadeType.MERGE
		})
		@JoinTable(name = "region_courier",
		    joinColumns = @JoinColumn(name = "region_id"),
		    inverseJoinColumns = @JoinColumn(name = "courier_id")
		)
	private Set<Courier> courier = new HashSet<>();

	public Long getRegion_id() {
		return region_id;
	}

	public void setRegion_id(Long region_id) {
		this.region_id = region_id;
	}

	public Set<Courier> getCourier() {
		return courier;
	}

	public void setCourier(Set<Courier> courier) {
		this.courier = courier;
	}

	private static final long serialVersionUID = 1L;

	public Region() {
		super();
		threshold = 10; // The default is that the distribution algorithm will run on this region if this region have more then 10 deliveries from type 0 and type 1.
	}
   
	public Long getId() {
		return region_id;
	}

	public void setId(Long region_id) {
		this.region_id = region_id;
	}
	
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getThreshold() {
		return threshold;
	}

	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	public List<Delivery> getDelivery() {
		return delivery;
	}

	public void setDelivery(List<Delivery> delivery) {
		this.delivery = delivery;
	}
	
}
