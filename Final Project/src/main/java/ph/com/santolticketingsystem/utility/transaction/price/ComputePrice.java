package ph.com.santolticketingsystem.utility.transaction.price;

import java.util.ArrayList;

public class ComputePrice {
	
	private ArrayList<String> stations = new ArrayList<String>();
	private String start = "";
	private String end = "";
	private String bound = "";
	private Double price = 13.00;
	private String passengerType = "regular";
	private int passed = 0;
	
	public ComputePrice(String start, String end, String bound, String passengerType) {
		this.start = start;
		this.end = end;
		this.bound = bound;
		this.passengerType = passengerType;
		
		stations.add("North-Avenue");
		stations.add("Quezon-Ave");
		stations.add("Kamuning");
		stations.add("Cubao");
		stations.add("Santolan-Annapolis");
		stations.add("Ortigas");
		stations.add("Shaw-Boulevard");
		stations.add("Boni-Avenue");
		stations.add("Guadalupe");
		stations.add("Buendia");
		stations.add("Ayala");
		stations.add("Magallanes");
		stations.add("Taft-Avenue");
	}
	
	
	
	public Double Compute() {
		if(this.bound == "northbound"){
			int a = stations.indexOf(start);
			int b = stations.indexOf(end);
			this.passed = a - b;
			int c = passed * 4;
			this.price += c;
    	}
		else if(this.bound == "southbound"){
			int a = stations.indexOf(start);
			int b = stations.indexOf(end);
			this.passed = b - a;
			int c = passed * 4;
			this.price += c;
    	}
		if(this.passengerType.equalsIgnoreCase("PWD") && this.price >= 50)
		{
			this.price *= 0.20;
			
		}
		else if(this.passengerType.equalsIgnoreCase("Student") && this.price >= 50)
		{
			this.price *= 0.20;
			
		}
		return this.price;
	}
}
