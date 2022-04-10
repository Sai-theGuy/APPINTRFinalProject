package ph.com.santolticketingsystem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		String passengerType = (String)session.getAttribute("passengerType");
		
		if(username == null && passengerType == null) {
			String UserName = request.getParameter("UserName");
			String PassWord = request.getParameter("PassWord");
			
			request.setAttribute("UserName", UserName);
			request.setAttribute("PassWord", PassWord);
			request.getRequestDispatcher("UserAuthenticate").forward(request, response);
		}
		else {
			session.setAttribute("username", username);
			session.setAttribute("passengerType", passengerType);
			System.out.println("Current session state (HomeServlet): " + username + ", " + passengerType);
			System.out.println();
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
	}
}
