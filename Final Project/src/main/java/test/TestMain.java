package test;

import ph.com.santolticketingsystem.utility.transaction.price.ComputePrice;

public class TestMain {

	public static void main(String[] args) {
		ComputePrice cp = new ComputePrice("Taft-Avenue","North-Avenue","northbound", "discounted");
		 System.out.print(cp.Compute());
	}
}
