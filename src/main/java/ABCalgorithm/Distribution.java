package ABCalgorithm;

import java.util.HashMap;

public class Distribution {
	Long id;
	
	HashMap<String,Double> factors = new HashMap<String,Double>(3);
	
	Division[] divisions;
	
	public Distribution(int numOfDivisions){
		this.divisions = new Division[numOfDivisions];
	}
	
	public Distribution(){
		factors.put("UrgencyFactor", 0.0);
		factors.put("DistanceFactor", 0.0);
		factors.put("LoadFactor", 0.0);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public HashMap<String,Double> getFactors() {
		return factors;
	}
	public void setFactors(HashMap<String,Double> factors) {
		this.factors = factors;
	}
	public Division[] getDivisions() {
		return this.divisions;
	}
	public void setDivisions(Division[] divisions2) {
		this.divisions = divisions2;
	}
}
