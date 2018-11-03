package ABCalgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.smartcourier.beans.Courier;
import com.smartcourier.beans.Delivery;
import com.smartcourier.beans.Region;
import com.smartcourier.controllers.DeliveryController;

public class ABCalgorithm {
	//This algorithm is executing per region.

	int runtime = 30;  /*Algorithm can be run many times in order to see its robustness*/
	int maxCycle = 2500; /*The number of cycles for foraging {a stopping criteria}*/
	int NUM_OF_DISTRIBUTION_IN_REGION = 10;
	Distribution[] distributions;
	Region region;
	ArrayList<Courier> divisions; //Division correspond to courier.
	ArrayList<Delivery> deliveriesToDistributeInRegion;//This are the deliveries from type 0 in the region.
	
	//Initial food sources are produced for all employed bees: Number of distributions are generated randomly in each region
	//(Associating each delivery with a random courier in that region). Time complexity: O(D+C)⋅NumOfdistributions) =^* O(D).
	public void initial(Region region, ArrayList<Delivery> deliveriesToDistributeInRegion)
	{
		//Initialize objects for the algorithm.
		this.region = region;
		this.deliveriesToDistributeInRegion = deliveriesToDistributeInRegion;//This are the deliveries from type 0 in the region.
		//Initialize distributions
		this.distributions = new Distribution[NUM_OF_DISTRIBUTION_IN_REGION];
		for(int k = 0; k < NUM_OF_DISTRIBUTION_IN_REGION; k++){
			this.distributions[k] = new Distribution(region.getCourier().size());
		}
		//Associating each delivery with a random courier in that region
		for( int i = 0; i < NUM_OF_DISTRIBUTION_IN_REGION; i++){
			//Initialize divisions in the distribution
			Division[] divisions = new Division[region.getCourier().size()];
			Iterator<Courier> couriersIterator = region.getCourier().iterator();
			//Associate each division with a courier.
			for(int k = 0; k < region.getCourier().size(); k++){
				divisions[k] = new Division();
				divisions[k].setCourier(couriersIterator.next());
				divisions[k].getDeliveries().addAll(divisions[k].getCourier().getDelivery());//Add deliveries from type 0 and 1 to courier's deliveries from type 2 and 3.
			}
			//For each distribution randomize deliveries from type 0 to the divisions.
			for(int j = 0; j < deliveriesToDistributeInRegion.size() ;j++){
				int divisionIndex = (int )(Math.random() * divisions.length); //Randomize number between 1 to number of divisions (couriers) in the region.

				divisions[divisionIndex].getDeliveries().add(deliveriesToDistributeInRegion.get(j));//set delivery to randomized division.
			}
			//Set divisions to distribution.
			distributions[i].setDivisions(divisions);
		}
	}
	
	public void MemorizeBestSource(){
		
	}
	
	public void SendEmployedBees()
	{
		
		//DeliveryController deliveryController = new DeliveryController();
		//ArrayList<Delivery> allDeliveries = (ArrayList<Delivery>) deliveryController.getAllDeliverys();
	}
	
	public void CalculateProbabilities(){
		
	}
	
	public void SendOnlookerBees()
	{
		
	}
	
	public void sendScoutBees()
	{
		
	}
	


	
	public void runABCalgorithm(Region region, ArrayList<Delivery> deliveriesToDistributeInRegion)
	{
		int iter=0;
		int run=0;
		for(run=0; run < runtime; run++)
		{
			initial(region,deliveriesToDistributeInRegion);//Initialize each distribution's fitness with a random value.
			MemorizeBestSource();//save the best distribution's value.
			for (iter=0; iter < maxCycle; iter++)
			    { 
				SendEmployedBees();//employed bee phase - artificially employed bee generates a random solution that is a mutant of the original solution and replace it with the current solution if it have higher fitness (solution = distribution), and this is perform for each distribution*/
				CalculateProbabilities();//This will be called:bee.SendEmployedBees(); and inside it we will call the bee.CalculateProbabilities() function because this function does what the employed foragers should do. More detail: Transfer fitness value to probability (still higher probability is better solution).
				SendOnlookerBees();//Those bees perform the same operation as employed bees, they will create a mutants from each distribution and will replace his parameters with the tested distribution if it's have higher fitness. 
				sendScoutBees();//discard an distribution with minimum values for his parameter because we want to keep the random process.
				MemorizeBestSource();//save in globalMin the parameters of the distribution with the higher fitness.
			    }
		}
        System.out.println("beeColony finished!");
	}
}
