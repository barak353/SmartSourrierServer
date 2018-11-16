import java.util.Hashtable;

import ABCalgorithm.ABCalgorithm;

//import com.smartcourier.beans.Customer;
//import com.smartcourier.dao.CustomerDao;

public class tester {

	public static void main(String[] args) {
//	
//		CustomerDao customerDao = new CustomerDao();
//		//public Customer(long customerID, String customerName, String customerAddress, String customerPhonenNumber)
//		
//		Customer customer = new Customer(2,"blablablA","GOFUCKYOURSELFDRIVE","XXX333");
//		customerDao.save(customer);
//		
//		System.out.println(customer.toString());
//		
//		Customer customerResponse = new Customer();
////		customerResponse = customerDao.findCustomerByID(2);
//		System.out.println(customerResponse.toString());
//		
		Hashtable<String,Double> test = new Hashtable<String,Double>();
		test.put("a", 0.0);
		test.put("b", 1.1);
		String a = "a";
		String b = "b";
		System.out.println(test.get(b));
	}

}
