package ph.com.santolticketingsystem.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/TicketMachine")
public class TicketMachineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		String passengerType = (String)session.getAttribute("passengerType");
		
		System.out.println("Current session state (TicketMachineServlet): " + username + ", " + passengerType);
		System.out.println();
		
		if(username == null && passengerType == null) {
			String UserName = request.getParameter("UserName");
			String PassWord = request.getParameter("PassWord");
			
			request.setAttribute("UserName", UserName);
			request.setAttribute("PassWord", PassWord);
			System.out.println("Redirecting to UserAuthenticate (TicketMachineServlet)");
			System.out.println();
			request.getRequestDispatcher("UserAuthenticate").forward(request, response);
		}
		else {
			session.setAttribute("username", username);
			session.setAttribute("passengerType", passengerType);
			System.out.println("Redirecting to buyticket.jsp (TicketMachineServlet)");
			System.out.println();
			request.getRequestDispatcher("buyticket.jsp").forward(request, response);
		}
	}

}
