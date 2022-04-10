package ph.com.santolticketingsystem.controller;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ph.com.santolticketingsystem.model.user.transaction.UserTransaction;
import ph.com.santolticketingsystem.utility.iterator.UserIterator;
import ph.com.santolticketingsystem.utility.singleton.SingletonDB;


@WebServlet("/Transactions")
public class TransactionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		String passengerType = (String)session.getAttribute("passengerType");
		
		System.out.println("Current session state (TransactionsServlet): " + username + ", " + passengerType);
		System.out.println();
		
		if(username == null && passengerType == null) {
			String UserName = request.getParameter("UserName");
			String PassWord = request.getParameter("PassWord");
			
			request.setAttribute("UserName", UserName);
			request.setAttribute("PassWord", PassWord);
			request.getRequestDispatcher("UserAuthenticate").forward(request, response);
		}
		else {
			UserIterator transactions = SingletonDB.selectTransactions(
				getServletContext().getInitParameter("jdbcDriver")
				, getServletContext().getInitParameter("jdbcUrl")
				, getServletContext().getInitParameter("dbUserName")
				, getServletContext().getInitParameter("dbUserPassword")
				, username
			);
			
			Iterator<UserTransaction> trans = transactions.createIterator();
			int i = 0;
			while(trans.hasNext()) {
				UserTransaction tranz =  trans.next();
				System.out.println("ID: " + tranz.getTransactionID() + ", Username: " + tranz.getUsername() + ", DateTime: " + tranz.getDateTime() 
					+ ", Start: " + tranz.getStart() + ", Stop: " + tranz.getDestination() + ", Price: " + tranz.getPrice());
				
				request.setAttribute("TransactionID"+i, tranz.getTransactionID());
				request.setAttribute("Username"+i, tranz.getUsername());
				request.setAttribute("DateTime"+i, tranz.getDateTime());
				request.setAttribute("Start"+i, tranz.getStart());
				request.setAttribute("Stop"+i, tranz.getDestination());
				request.setAttribute("Price"+i, tranz.getPrice());
				
				i++;
			}
			System.out.println();
			
			System.out.println("Redirecting to transactions.jsp (TransactionsServlet)");
			System.out.println();
			request.setAttribute("length", i);
			request.getRequestDispatcher("transactions.jsp").include(request, response);
		}
	}
}
