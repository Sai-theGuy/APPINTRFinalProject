package ph.com.santolticketingsystem.model.user.transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ph.com.santolticketingsystem.utility.iterator.UserIterator;

public class UserTransactions implements UserIterator{

	private List<UserTransaction> items = new ArrayList<UserTransaction>();	

	   public List<UserTransaction> getItems() {
		return items;
	}

	public void setItems(List<UserTransaction> items) {
		this.items = items;
	}

	public void addTransaction(UserTransaction transaction){
	      items.add(transaction);
	   }

	   public double getTotalPrice(){
	      double price = 0.0;
	      
	      for (UserTransaction item : items) {
	    	  price += item.getPrice();
	      }		
	      return price;
	   }

		@SuppressWarnings("rawtypes")
		@Override
		public Iterator createIterator() {
			return items.iterator();
		}
}
