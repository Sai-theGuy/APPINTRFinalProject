package ph.com.santolticketingsystem.model.user.transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ph.com.santolticketingsystem.utility.iterator.UserIterator;

public class UserTransactions implements UserIterator, Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<UserTransaction> items = new ArrayList<UserTransaction>();	

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
