package ABCalgorithm;

import java.util.ArrayList;
import com.smartcourier.beans.Region;

public class Distribution {
	Long id;
	
	Double[] factors;
	
	ArrayList<Division> divisions;
	
	Region region;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double[] getFactors() {
		return factors;
	}
	public void setFactors(Double[] factors) {
		this.factors = factors;
	}
	
	
}
