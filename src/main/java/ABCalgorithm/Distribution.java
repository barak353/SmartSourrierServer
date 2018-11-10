package ABCalgorithm;

import java.util.HashMap;

public class Distribution {
	Long id;
	
	HashMap<String,Double> factors = new HashMap<String,Double>(3);
	
	Division[] divisions;
	Double fitness;
	
	public Distribution(int numOfDivisions){
		this.divisions = new Division[numOfDivisions];
	}
	
	/*public Distribution(){
		factors.put("UrgencyFactor", 0.0);
		factors.put("DistanceFactor", 0.0);
		factors.put("LoadFactor", 0.0);
	}*/
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Division[] getDivisions() {
		return this.divisions;
	}
	public void setDivisions(Division[] divisions) {
		this.divisions = divisions;
	}

	public Double getFitness() {
		return fitness;
	}

	public void setFitness(Double fitness) {
		this.fitness = fitness;
	}
}
