package ph.com.santolticketingsystem.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ph.com.santolticketingsystem.model.user.transaction.UserTransaction;
import ph.com.santolticketingsystem.utility.facade.TicketMachineFacade;
import ph.com.santolticketingsystem.utility.singleton.SingletonDB;

@WebServlet("/Ticket")
public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String bound = request.getParameter("direction");
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		String passengerType = (String)session.getAttribute("passengerType");
		
		System.out.println("Current session state (TicketServlet): " + username + ", " + passengerType);
		System.out.println();
		
		if(username == null && passengerType == null) {
			String UserName = request.getParameter("UserName");
			String PassWord = request.getParameter("PassWord");
			
			request.setAttribute("UserName", UserName);
			request.setAttribute("PassWord", PassWord);
			System.out.println("Redirecting to UserAuthenticate (TicketServlet)");
			System.out.println();
			request.getRequestDispatcher("UserAuthenticate").forward(request, response);
		}
		else {
			TicketMachineFacade tran = new TicketMachineFacade();
			
			UserTransaction transaction = tran.buyTicket(username, start, end, bound, passengerType);
			
			System.out.println(transaction);
			
			SingletonDB.createTransactionsTable(
				getServletContext().getInitParameter("jdbcDriver")
				, getServletContext().getInitParameter("jdbcUrl")
				, getServletContext().getInitParameter("dbUserName")
				, getServletContext().getInitParameter("dbUserPassword")
			);
			//pass to database
			SingletonDB.insertTransaction(
				getServletContext().getInitParameter("jdbcDriver")
				, getServletContext().getInitParameter("jdbcUrl")
				, getServletContext().getInitParameter("dbUserName")
				, getServletContext().getInitParameter("dbUserPassword")
				,transaction
			);
			//redirect to
			System.out.println("Redirecting to Transaction (TicketServlet)");
			System.out.println();
			request.getRequestDispatcher("Transaction").forward(request, response);
		}
	}

}
