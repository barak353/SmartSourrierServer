package ABCalgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.web.client.RestTemplate;

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
	double maxDriveDistanceBetweenPairDeliveriesInRegion = 0.0;
	double totalNumOfUrgentDeliveriesInDistribution = 0;

	/*Initial food sources are produced for all employed bees: Number of distributions are generated randomly in each region
	 *(Associating each delivery with a random courier in that region). Time complexity: O(D+C)⋅NumOfdistributions) =* O(D).
	 */
	public void initial(Region region, ArrayList<Delivery> deliveriesToDistributeInRegion) throws Exception
	{
		//Initialize objects for the algorithm.
		this.region = region;
		this.deliveriesToDistributeInRegion = deliveriesToDistributeInRegion;//This are the deliveries from type 0 in the region.
		//Initialize distributions
		this.distributions = new Distribution[NUM_OF_DISTRIBUTION_IN_REGION];
		for(int k = 0; k < NUM_OF_DISTRIBUTION_IN_REGION; k++)
		{
			this.distributions[k] = new Distribution(region.getCourier().size());
		}
		//Associating each delivery with a random courier in that region
		for( int i = 0; i < NUM_OF_DISTRIBUTION_IN_REGION; i++)
		{
			//Initialize divisions in the distribution
			Division[] divisions = new Division[region.getCourier().size()];
			Iterator<Courier> couriersIterator = region.getCourier().iterator();
			//Associate each division with a courier.
			for(int k = 0; k < region.getCourier().size(); k++)
			{
				divisions[k] = new Division();
				divisions[k].setCourier(couriersIterator.next());
				divisions[k].getDeliveries().addAll(divisions[k].getCourier().getDelivery());//Add deliveries from type 0 and 1 to courier's deliveries from type 2 and 3.
			}
			//For each distribution randomize deliveries from type 0 to the divisions.
			for(int j = 0; j < deliveriesToDistributeInRegion.size() ;j++)
			{
				if(deliveriesToDistributeInRegion.get(i).getIsUrgent() == 1)
					totalNumOfUrgentDeliveriesInDistribution++;
				int divisionIndex = (int )(Math.random() * divisions.length); //Randomize number between 1 to number of divisions (couriers) in the region.
				divisions[divisionIndex].getDeliveries().add(deliveriesToDistributeInRegion.get(j));//set delivery to randomized division.
				for(int k = j + 1; k < deliveriesToDistributeInRegion.size(); k++)
				{
					Delivery origDelivery = deliveriesToDistributeInRegion.get(j);
					Delivery destDelivery = deliveriesToDistributeInRegion.get(k); 
					Double[] orig = {origDelivery.getLatitude(), origDelivery.getLongitude()};
					Double[] dest = {destDelivery.getLatitude(), destDelivery.getLongitude()};
					double driveDistance = getDriveDistance(orig, dest);
					if(maxDriveDistanceBetweenPairDeliveriesInRegion < driveDistance)
						maxDriveDistanceBetweenPairDeliveriesInRegion = driveDistance;
				}
			}
			//Set divisions to distribution.
			distributions[i].setDivisions(divisions);
		}
	}
	
	/*
	 * Each employed bee goes to a food source in her memory and determines a closest source, then evaluates its nectar amount and dances in the hive: 
	 * The fitness of each distribution is being calculated. Time complexity: 𝑂(𝐷^2⋅𝑁𝑢𝑚𝑂𝑓𝑑𝑖𝑠𝑡𝑟𝑖𝑏𝑢𝑡𝑖𝑜𝑛𝑠)=∗𝑂(𝐷^2).
	 * */
	public void SendEmployedBees() throws Exception
	{
		for(Distribution distribution : distributions)
		{
			Double[] factorsProbabilities = {0.0, 0.0, 0.0};
			double maximumNumberOfUrgentDeliveriesInADivision = 0;
			double totalAvgsOfTotalDistancesBetweenDeliveriesInDivision = 0.0;
			for(Division division : distribution.getDivisions())
			{
				double numberOfUrgentDeliveriesInDivision = 0;
				double totalDistancesBetweenDeliveriesInDivision = 0.0;
				ArrayList<Delivery> deliveries = division.getDeliveries();
				for(Delivery delivery: deliveries)
				{
					//UrgencyFactor
					if(delivery.getIsUrgent() == 1)
						numberOfUrgentDeliveriesInDivision++;
					//DistanceFactor
					int i = deliveries.indexOf(delivery);
					for(; i < deliveries.size() ;i++)
					{
						Delivery destDelivery = deliveries.get(i);
						Double[] orig = {delivery.getLatitude(), delivery.getLongitude()};
						Double[] dest = {destDelivery.getLatitude(), destDelivery.getLongitude()};
						totalDistancesBetweenDeliveriesInDivision += getDriveDistance(orig, dest);
					}
					if(maximumNumberOfUrgentDeliveriesInADivision < numberOfUrgentDeliveriesInDivision)
						maximumNumberOfUrgentDeliveriesInADivision = numberOfUrgentDeliveriesInDivision;
					//LoadFactor (most important)
					//ToAdd
				}
				double numOfPairsOfDeliveriesInDivision = (division.getDeliveries().size() * division.getDeliveries().size() - 1) / 2;//Hand shake lemma;
				totalAvgsOfTotalDistancesBetweenDeliveriesInDivision += (totalDistancesBetweenDeliveriesInDivision / numOfPairsOfDeliveriesInDivision);
			}
			double avgOfTotalAvgDistancesInDivision = (totalAvgsOfTotalDistancesBetweenDeliveriesInDivision / distribution.getDivisions().length);
			factorsProbabilities[1] = 1 - ( avgOfTotalAvgDistancesInDivision / maxDriveDistanceBetweenPairDeliveriesInRegion);
			factorsProbabilities[3] = 1 - ( maximumNumberOfUrgentDeliveriesInADivision / totalNumOfUrgentDeliveriesInDistribution);
			//total fitness
			Double distributionFitness = 0.0;
			for(int i = 1; i < 3; i++)
				distributionFitness += factorsProbabilities[i] * (double) i;
			distribution.setFitness(distributionFitness);
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
	
	public void SendOnlookerBees()
	{
		
	}
	
	public void sendScoutBees()
	{
		
	}
	
	public void runABCalgorithm(Region region, ArrayList<Delivery> deliveriesToDistributeInRegion) throws Exception
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
				MemorizeBestSource();
				sendScoutBees();
				}
		}
//		getEmployees
//        System.out.println("beeColony finished!");
//		Double[] orig = {30.0200, 30.0400};
//		Double[] dest = {30.0400, 30.0800};

//		getDriveDistance(orig, dest);
	}
	
	private static Double getDriveDistance(Double[] origin, Double[] destination) throws Exception
	{
	    String uri = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial$&origins={latitude-origin},{longitude-origin}&destinations={latitude-destination},{longitude-destination}&key=AIzaSyB1zPXsprtP41UCI9zd30FZkwJf-U1p5Ao";
	    uri = uri.replace("{latitude-origin}", origin[0].toString());
	    uri = uri.replace("{longitude-origin}", origin[1].toString());
	    uri = uri.replace("{latitude-destination}", destination[0].toString());
	    uri = uri.replace("{longitude-destination}", destination[1].toString());
	    RestTemplate restTemplate = new RestTemplate();
	    String jsonResult  = restTemplate.getForObject(uri, String.class);
	    int ln = jsonResult.split("text\" : \"").length;
	    if(jsonResult.split("text\" : \"").length > 1)
	    {
	    	String driveDistanceInKm = jsonResult.split("text\" : \"")[1].split("m\"")[0];
	    	return Double.parseDouble(driveDistanceInKm);
	    }
		throw new Exception("Client should never except latitude and attitude that are not related to real address!");
	}
}
