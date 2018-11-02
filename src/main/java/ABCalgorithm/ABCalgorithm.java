package ABCalgorithm;

import java.util.ArrayList;
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
	ArrayList<Distribution> distributions = new ArrayList<Distribution>(10);
	Region region;
	ArrayList<Courier> CouriersInRegion;
	ArrayList<Delivery> deliveriesInRegion;
	public void initial(Region region, ArrayList<Delivery> deliveriesToDistributeInRegion)
	{
		//Initialize objects for the algoritm.
		this.region = region;
		this.CouriersInRegion = new ArrayList<Courier>(region.getCourier());
		this.deliveriesInRegion = new ArrayList<Delivery>(region.getDelivery());
		
		//Associating each delivery with a random courier in that region
		

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
	
	public void MemorizeBestSource(){
		
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
