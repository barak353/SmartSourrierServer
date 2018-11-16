package ABCalgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
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
	private int runtime = 3;  /*Algorithm can be run many times in order to see its robustness*/
	private int maxCycle = 10; /*The number of cycles for foraging {a stopping criteria}*/
	private static final int NUM_OF_DISTRIBUTION_IN_REGION = 10;
	private Distribution[] distributions;
	private ArrayList<Delivery> deliveriesToDistributeInRegion;//This are the deliveries from type 0 in the region.
	double maxDriveDistanceBetweenPairDeliveriesInRegion = 0.0;
	double totalNumOfUrgentDeliveriesInDistribution = 0;
	double numOfDeliveriesToDistributeInRegion = 0.0;
	double higestPossibleValueForDriveDistance = 0.0;
	int num = 0;
	Distribution bestDistribution;
	//#####Testing##########Testing##########Testing##########Testing##########Testing#####
	static Hashtable<String, Hashtable<String,Double> > distancesTest = new Hashtable<String, Hashtable<String,Double> >();
	
	private void setDriveDistanceTest(Double[] orig, Double[] dest, Double distance)
	{
		String origStr = String.valueOf(orig[0]) + "," + String.valueOf(orig[1]);
		String destStr = String.valueOf(dest[0]) + "," + String.valueOf(dest[1]);
		Hashtable<String, Double> destenations;
		if(distancesTest.containsKey(origStr))
			destenations = distancesTest.get(origStr);
		else
			destenations =  new Hashtable<String,Double>();
		destenations.put(destStr, distance);
		distancesTest.put(destStr, destenations);
	}

	private static Double getDriveDistanceTest(Double[] orig, Double[] dest)
	{
		String origStr = String.valueOf(orig[0]) + "," + String.valueOf(orig[1]);
		String destStr = String.valueOf(dest[0]) + "," + String.valueOf(dest[1]);
		Hashtable<String, Double> distancesHast = distancesTest.get(origStr);
		Double distance = distancesHast.get(destStr);
	    return distance;
	}
	
	private void generateDistancesTest()
	{
		for(int j = 0; j < deliveriesToDistributeInRegion.size() ;j++)
		{
			for(int k = 0; k < deliveriesToDistributeInRegion.size(); k++)
			{
				
				Delivery origDelivery = deliveriesToDistributeInRegion.get(j);
				Delivery destDelivery = deliveriesToDistributeInRegion.get(k); 
				Double[] orig = {origDelivery.getLatitude(), origDelivery.getLongitude()};
				Double[] dest = {destDelivery.getLatitude(), destDelivery.getLongitude()};
				double distance;
				if(j == k)
					distance = 0;
				else
					distance = (double )(1 + Math.random() * 49); //Randomize number between 1 to number of divisions (couriers) in the region.
				setDriveDistanceTest(orig, dest, distance);
				System.out.println(distancesTest.toString());
				if(distancesTest.get(orig) != null)
					System.out.println(distancesTest.get(orig).toString());
				System.out.println("-----------------------------------------------------");
			}
		}
	}
	//#####Testing##########Testing##########Testing##########Testing##########Testing#####
	
	/*Initial food sources are produced for all employed bees: Number of distributions are generated randomly in each region
	 *(Associating each delivery with a random courier in that region). Time complexity: O(D+C)⋅NumOfdistributions) =* O(D).
	 */
	public void initial(Region region, ArrayList<Delivery> deliveriesToDistributeInRegion) throws Exception
	{
		//test
		maxDriveDistanceBetweenPairDeliveriesInRegion = 0.0;
		totalNumOfUrgentDeliveriesInDistribution = 0.0;
		//Initialize objects for the algorithm.
		this.deliveriesToDistributeInRegion = deliveriesToDistributeInRegion;//This are the deliveries from type 0 in the region.
		this.numOfDeliveriesToDistributeInRegion = deliveriesToDistributeInRegion.size(); //This needed for the load factor.
		//Initialize distributions
		this.distributions = new Distribution[NUM_OF_DISTRIBUTION_IN_REGION];
		generateDistancesTest();
		for(int k = 0; k < NUM_OF_DISTRIBUTION_IN_REGION; k++)
		{
			this.distributions[k] = new Distribution(region.getCourier().size());
		}
		//Count the total number of urgent deliveries.
		for(int j = 0; j < deliveriesToDistributeInRegion.size() ;j++)
		{
			if(deliveriesToDistributeInRegion.get(j).getIsUrgent() == 1)
				totalNumOfUrgentDeliveriesInDistribution++;
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
			}
			//For each distribution randomize deliveries from type 0 or 1 to the divisions.
			for(int j = 0; j < deliveriesToDistributeInRegion.size() ;j++)
			{
				int divisionIndex = (int )(Math.random() * divisions.length); //Randomize number between 1 to number of divisions (couriers) in the region.
				divisions[divisionIndex].getDeliveries().add(deliveriesToDistributeInRegion.get(j));//set delivery to randomized division.
			}
			//Set divisions to distribution.
			distributions[i].setDivisions(divisions);
		}
		//Find longest driving distance between pair of deliveries in region.
		for(int j = 0; j < deliveriesToDistributeInRegion.size() ;j++)
		{
			for(int k = j + 1; k < deliveriesToDistributeInRegion.size(); k++)
			{
				Delivery origDelivery = deliveriesToDistributeInRegion.get(j);
				Delivery destDelivery = deliveriesToDistributeInRegion.get(k); 
				Double[] orig = {origDelivery.getLatitude(), origDelivery.getLongitude()};
				Double[] dest = {destDelivery.getLatitude(), destDelivery.getLongitude()};
				double driveDistance = getDriveDistanceTest(orig, dest);
				this.higestPossibleValueForDriveDistance += driveDistance;
				if(maxDriveDistanceBetweenPairDeliveriesInRegion < driveDistance)
					maxDriveDistanceBetweenPairDeliveriesInRegion = driveDistance;
			}
		}
	}
	/*
	 * Each employed bee goes to a food source in her memory and determines a closest source, then evaluates its nectar amount and dances in the hive: 
	 * The fitness of each distribution is being calculated. Time complexity: 𝑂(𝐷^2⋅𝑁𝑢𝑚𝑂𝑓𝑑𝑖𝑠𝑡𝑟𝑖𝑏𝑢𝑡𝑖𝑜𝑛𝑠)=∗𝑂(𝐷^2).
	 * */
	public void SendEmployedBees() throws Exception
	{
		int cut = 0;//For debugging
		for(Distribution distribution : distributions)
		{
			double sumTotalDistancesBetweenDeliveriesInDivision = 0.0;
			System.out.println("--------------------------------Distribution #"+cut+"--------------------------------");
			Double[] factorsProbabilities = {0.0, 0.0, 0.0, 0.0};
			double maximumNumberOfUrgentDeliveriesInADivision = 0;
			double maxTotalDistancesBetweenDeliveriesInDivision = 0.0;
			double minTotalDistancesBetweenDeliveriesInDivision = this.maxDriveDistanceBetweenPairDeliveriesInRegion * this.higestPossibleValueForDriveDistance;
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
					int i = deliveries.indexOf(delivery) + 1;
					for(; i < deliveries.size() ;i++)
					{
						Delivery destDelivery = deliveries.get(i);
						Double[] orig = {delivery.getLatitude(), delivery.getLongitude()};
						Double[] dest = {destDelivery.getLatitude(), destDelivery.getLongitude()};
						totalDistancesBetweenDeliveriesInDivision += getDriveDistanceTest(orig, dest);
					}
					if(maximumNumberOfUrgentDeliveriesInADivision < numberOfUrgentDeliveriesInDivision)
						maximumNumberOfUrgentDeliveriesInADivision = numberOfUrgentDeliveriesInDivision;
				}
				double numOfPairsOfDeliveriesInDivision = (division.getDeliveries().size() * (division.getDeliveries().size() - 1) ) / 2;//Hand shake lemma;
				//loadDistanceFactor
				if( totalDistancesBetweenDeliveriesInDivision > maxTotalDistancesBetweenDeliveriesInDivision)
					maxTotalDistancesBetweenDeliveriesInDivision = totalDistancesBetweenDeliveriesInDivision;
				if( totalDistancesBetweenDeliveriesInDivision < minTotalDistancesBetweenDeliveriesInDivision)
					minTotalDistancesBetweenDeliveriesInDivision = totalDistancesBetweenDeliveriesInDivision; 
				System.out.println("division-courier-Id: "+ division.getCourier().getId() + " -> totalDistancesBetweenDeliveriesInDivision: " + totalDistancesBetweenDeliveriesInDivision + " , (totalDistancesBetweenDeliveriesInDivision / numOfPairsOfDeliveriesInDivision) = " +(totalDistancesBetweenDeliveriesInDivision / numOfPairsOfDeliveriesInDivision));
				sumTotalDistancesBetweenDeliveriesInDivision += totalDistancesBetweenDeliveriesInDivision;
			}
			System.out.println("sumTotalDistancesBetweenDeliveriesInDivision: " + sumTotalDistancesBetweenDeliveriesInDivision);
			System.out.println("minTotalDistancesBetweenDeliveriesInDivision: "+ minTotalDistancesBetweenDeliveriesInDivision +" , maxTotalDistancesBetweenDeliveriesInDivision: " + maxTotalDistancesBetweenDeliveriesInDivision );
			//LoadFactor 
			double maximumNumberOfDeliveriesInDivision = 0.0;
			double minimumNumberOfDeliveriesInDivision = this.numOfDeliveriesToDistributeInRegion;
			if(distribution.getDivisions()[0].getDeliveries().size() > 0)
			{
				for(Division division: distribution.getDivisions())
				{
					//loadFactor
					if(division.getDeliveries().size() > maximumNumberOfDeliveriesInDivision)
						maximumNumberOfDeliveriesInDivision = division.getDeliveries().size();
					if(division.getDeliveries().size() < minimumNumberOfDeliveriesInDivision)
						minimumNumberOfDeliveriesInDivision = division.getDeliveries().size();
				}
			}
			double loadFactor = maximumNumberOfDeliveriesInDivision - minimumNumberOfDeliveriesInDivision;//
			double loadDistanceFactor = maxTotalDistancesBetweenDeliveriesInDivision;
			double urgentFactor = maximumNumberOfUrgentDeliveriesInADivision; 
			double drivingDistanceFactor = sumTotalDistancesBetweenDeliveriesInDivision;
			System.out.println("loadFactor: " + loadFactor + ", loadDistanceFactor: " + loadDistanceFactor + ", urgentFactor: " + urgentFactor + ", drivingDistanceFactor: " + drivingDistanceFactor);
			factorsProbabilities[3] = 1 - ( drivingDistanceFactor / this.higestPossibleValueForDriveDistance);
			factorsProbabilities[2] = 1 - ( loadDistanceFactor / this.higestPossibleValueForDriveDistance);
			factorsProbabilities[1] = 1 - ( loadFactor / this.numOfDeliveriesToDistributeInRegion);
			factorsProbabilities[0] = 1 - ( urgentFactor / totalNumOfUrgentDeliveriesInDistribution);//Less important
			System.out.println("factorsProbabilities[3]: "+factorsProbabilities[3] + ", maxDriveDistanceBetweenPairDeliveriesInRegion="+maxDriveDistanceBetweenPairDeliveriesInRegion);
			System.out.println("factorsProbabilities[2]: "+factorsProbabilities[2]);
			System.out.println("factorsProbabilities[1]: "+factorsProbabilities[1]);
			System.out.println("factorsProbabilities[0]: "+factorsProbabilities[0]);
			//total fitness
			Double distributionFitness = 0.0;
			for(int i = 1; i <= 4; i++)
				distributionFitness += factorsProbabilities[i - 1] * (double) (i);
			distribution.setFitness(distributionFitness);
			printLog(distribution, distributionFitness);
			cut++;//fpt debugging
		}
	}
	
	/*This method calculate the probabilities for each factor and then calculate the fitness of each distribution. 
	 * Then finds and stores the distribution with the maximum fitness. Time complexity: 𝑂(𝑁𝑢𝑚𝑂𝑓𝑑𝑖𝑠𝑡𝑟𝑖𝑏𝑢𝑡𝑖𝑜𝑛𝑠∗C)=∗ 𝑂(C).
	 */
	public void MemorizeBestSource()
	{	
		this.bestDistribution = this.distributions[0];
		for(Distribution distribution : this.distributions)
		{
			if(this.bestDistribution.fitness < distribution.fitness)
				this.bestDistribution = distribution; 
		}
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
		//After the algorithm distibute deliveries to couriers we need to change their type to type 2.
	}
	

	private static Double getDriveDistance(Double[] origin, Double[] destination) throws Exception
	{
	    String uri = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial$&origins={latitude-origin},{longitude-origin}&destinations={latitude-destination},{longitude-destination}&key=My-Key";
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
	    	if(driveDistanceInKm.toLowerCase().contains("k"))//If the distance is in km remove the k from the km.
	    		driveDistanceInKm = driveDistanceInKm.substring(0, driveDistanceInKm.length() - 1);
	    	return Double.parseDouble(driveDistanceInKm);
	    }
		throw new Exception("Client should never except latitude and attitude that are not related to real address!");
	}
	
	private void printLog(Distribution distribution, double fitness)//, double avgOfTotalAvgDistancesInDivisions)
	{
		int count = 0;
		System.out.println("Distribution #"+num+" details:");
		System.out.println("Fitness: " + fitness);
		for(Division division: distribution.getDivisions())
		{
			int count2 = 0;
			System.out.println("  (division-courier-id: " + division.getCourier().getId() + ") Division #"+count+":");
			for(Delivery delivery: division.getDeliveries())
			{
				System.out.println("		Delivery #"+count2+":");
				System.out.println("			isUrgent: "+ delivery.getIsUrgent());
				System.out.println("			Latitude,Longitude: "+ delivery.getLatitude()+ "," + delivery.getLongitude());
				count2++;
			}
			count++;
		}
		num++;
		//System.out.println("avgOfTotalAvgDistancesInDivisions = "+ avgOfTotalAvgDistancesInDivisions);
		System.out.println("##-------------------------------------------------------------------------------##");
	}
}
