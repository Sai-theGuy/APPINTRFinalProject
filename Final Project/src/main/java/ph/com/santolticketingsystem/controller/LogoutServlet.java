package ph.com.santolticketingsystem.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		String passengerType = (String)session.getAttribute("passengerType");
		
		System.out.println("Before session destroy: " + username + ", " + passengerType);
		System.out.println();
		
		if(username == null && passengerType == null) {
			request.getRequestDispatcher("Login").forward(request, response);
		}
		else {
			session.removeAttribute("username");
			session.removeAttribute("passengerType");
			System.out.println("You have successfully logged out.");
			System.out.println();
			session.setAttribute("successMessage", "You have successfully logged out.");
			System.out.println("Redirecting to Login (LogoutServlet)");
			System.out.println();
	        request.getRequestDispatcher("Login").include(request, response);
	        return;
		}
		
        
	}

}
