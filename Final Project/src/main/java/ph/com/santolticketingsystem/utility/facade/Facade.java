package ph.com.santolticketingsystem.utility.facade;

import ph.com.santolticketingsystem.model.user.transaction.UserTransaction;

public interface Facade {
	public UserTransaction buyTicket(String username, String start, String end, String direction, String passengerType);
}
