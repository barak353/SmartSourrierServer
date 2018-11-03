package ABCalgorithm;

public class Distribution {
	Long id;
	
	Double[] factors;
	
	Division[] divisions;
	
	public Distribution(int numOfDivisions){
		this.divisions = new Division[numOfDivisions];
	}

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
	public Division[] getDivisions() {
		return this.divisions;
	}
	public void setDivisions(Division[] divisions2) {
		this.divisions = divisions2;
	}
}
