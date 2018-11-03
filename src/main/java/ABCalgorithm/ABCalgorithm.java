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

	private int runtime = 30;  /*Algorithm can be run many times in order to see its robustness*/
	private int maxCycle = 2500; /*The number of cycles for foraging {a stopping criteria}*/
	private static final int NUM_OF_DISTRIBUTION_IN_REGION = 10;
	private static final int NUM_OF_FACTORS = 3;
	private Distribution[] distributions;
	private Region region;
	private ArrayList<Delivery> deliveriesToDistributeInRegion;//This are the deliveries from type 0 in the region.
	
	/*Initial food sources are produced for all employed bees: Number of distributions are generated randomly in each region
	 *(Associating each delivery with a random courier in that region). Time complexity: O(D+C)⋅NumOfdistributions) =* O(D).
	 */
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
	
	/*This method calculate the probabilities for each factor and then calculate the fitness of each distribution. 
	 * Then finds and stores the distribution with the maximum fitness. Time complexity: 𝑂(𝑁𝑢𝑚𝑂𝑓𝑑𝑖𝑠𝑡𝑟𝑖𝑏𝑢𝑡𝑖𝑜𝑛𝑠∗C)=∗ 𝑂(C).
	 */
	public void MemorizeBestSource(){
		
		
		/*for( int index = 0; index < NUM_OF_DISTRIBUTION_IN_REGION; index++){
			factorsProbabilities[index] = 
		}*/
	}
	/*
	 * Each employed bee goes to a food source in her memory and determines a closest source, then evaluates its nectar amount and dances in the hive: 
	 * The fitness of each distribution is being calculated. Time complexity: 𝑂(𝐷^2⋅𝑁𝑢𝑚𝑂𝑓𝑑𝑖𝑠𝑡𝑟𝑖𝑏𝑢𝑡𝑖𝑜𝑛𝑠)=∗𝑂(𝐷^2).
	 * */
	public void SendEmployedBees()
	{
		for(Distribution distribution : distributions){
			int maxUrgentDeliveriesInDistribution = 0;
			for(Division division : distribution.getDivisions()){
				int numberOfUrgentDeliveriesInDivision = 0;
				for(Delivery delivery: division.getDeliveries()){
					if(delivery.getIsUrgent() == 1)
						numberOfUrgentDeliveriesInDivision++;
				}
				if(maxUrgentDeliveriesInDistribution < numberOfUrgentDeliveriesInDivision)
					maxUrgentDeliveriesInDistribution = numberOfUrgentDeliveriesInDivision;
			}			
			distribution.factors.put("UrgencyFactor", (double) maxUrgentDeliveriesInDistribution);
		}
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
			initial(region,deliveriesToDistributeInRegion);
			SendEmployedBees();
			MemorizeBestSource();
			for (iter=0; iter < maxCycle; iter++)
			    { 
				SendOnlookerBees(); 
				sendScoutBees();
				MemorizeBestSource();
				}
		}
        System.out.println("beeColony finished!");
	}
}
