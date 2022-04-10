package ph.com.santolticketingsystem.model.user.transaction;

import java.util.Iterator;

public class UserTransaction {
	
	// for retrieval
	public UserTransaction(int TransactionID, String Username, String DateTime, String Start, String Destination, Double Price)
	{
		this.TransactionID = TransactionID;
		this.Username = Username;
		this.DateTime = DateTime;
		this.Start = Start;
		this.Destination = Destination;
		this.Price = Price;
	}
	
	// for creating transactions
	public UserTransaction(String Username, String Start, String Destination, Double Price)
	{
		this.Username = Username;
		this.Start = Start;
		this.Destination = Destination;
		this.Price = Price;
	}
	
	private int TransactionID;
	private String Username;
	private String DateTime;
	private String Start;
	private String Destination;
	private Double Price;
	
	public int getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(int transactionID) {
		TransactionID = transactionID;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getDateTime() {
		return DateTime;
	}
	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}
	public String getStart() {
		return Start;
	}
	public void setStart(String start) {
		Start = start;
	}
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	public Double getPrice() {
		return Price;
	}
	public void setPrice(Double price) {
		Price = price;
	}

}
