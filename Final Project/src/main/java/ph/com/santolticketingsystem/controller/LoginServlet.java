package ph.com.santolticketingsystem.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		String passengerType = (String)session.getAttribute("passengerType");
		
		System.out.println("Current session state (LoginServlet): " + username + ", " + passengerType);
		System.out.println();
		
		if(username != null && passengerType != null){	
			request.getRequestDispatcher("Home").forward(request, response);
		}	
		else {
			System.out.println("Redirecting to index.jsp (LoginServlet)");
			System.out.println();
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}

}
