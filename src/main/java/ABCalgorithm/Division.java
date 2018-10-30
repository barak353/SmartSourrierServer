package ABCalgorithm;

import java.util.ArrayList;
import com.smartcourier.beans.Courier;
import com.smartcourier.beans.Delivery;
import com.smartcourier.beans.Region;

public class Division {
	Long id;
	
	ArrayList<Delivery> deliveries;
	
	Region region;
	
	Distribution distribution;
	
	Courier courier;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArrayList<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(ArrayList<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Distribution getDistribution() {
		return distribution;
	}

	public void setDistribution(Distribution distribution) {
		this.distribution = distribution;
	}

	public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}
	
	
	
}
