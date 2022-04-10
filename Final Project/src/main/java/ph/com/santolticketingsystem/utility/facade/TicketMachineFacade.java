package ph.com.santolticketingsystem.utility.facade;

import ph.com.santolticketingsystem.model.user.transaction.UserTransaction;
import ph.com.santolticketingsystem.utility.transaction.price.ComputePrice;

public class TicketMachineFacade implements Facade{

	// buying ticket
	@Override
	public UserTransaction buyTicket(String username, String start, String end, String bound, String passengerType) {
		
		ComputePrice cp = new ComputePrice(start, end, bound, passengerType);
		UserTransaction ticketTrip = new UserTransaction(username, start, end, cp.Compute());
		
		return ticketTrip;
	}
}
