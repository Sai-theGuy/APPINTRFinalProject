package ph.com.santolticketingsystem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ph.com.santolticketingsystem.model.information.LoginCredential;
import ph.com.santolticketingsystem.model.information.LoginInformation;
import ph.com.santolticketingsystem.utility.singleton.SingletonDB;


@WebServlet("/UserAuthenticate")
public class UserAuthenticateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		// get input from user
		 String UserName = request.getParameter("UserName");
		 String PassWord = request.getParameter("PassWord");
			
		// these are temporary params, these should come from database
		 LoginCredential inputCreds = new LoginCredential(UserName, PassWord);
		 
		 LoginInformation li = SingletonDB.selectLogin(
			getServletContext().getInitParameter("jdbcDriver")
			, getServletContext().getInitParameter("jdbcUrl")
			, getServletContext().getInitParameter("dbUserName")
			, getServletContext().getInitParameter("dbUserPassword")
			,inputCreds);
				
		if(li != null){
			// if valid login
			// start session
			HttpSession session = request.getSession();
			String username = (String)session.getAttribute("username");
			String passengerType = (String)session.getAttribute("passengerType");
			
			if(username == null && passengerType == null){
				username = li.getUserName();
				session.setAttribute("username", username);
						
				passengerType = li.getPassengerType();
				session.setAttribute("passengerType", passengerType);
				System.out.println("Session start: " + username + ", " + passengerType);
				System.out.println();
			}
					
			if(username != null || passengerType != null){	
				// redirect to home page
				System.out.println("Current session state (UserAuthenticateServlet): " + username + ", " + passengerType);
				System.out.println("Redirecting to Home (UserAuthenticateServlet)");
				System.out.println();
				request.getRequestDispatcher("Home").forward(request, response);
			}
			else {
				System.out.println("Session setup failed");
			}
		}
		else {
			// if not valid login
			// set a error message first before sending the response
			request.setAttribute("errorMessage", "Access Denied. Invalid Credentials.");
			request.getRequestDispatcher("Login").forward(request, response);
		}
				
	}

}
