package ph.com.santolticketingsystem.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ph.com.santolticketingsystem.model.user.authentcator.AuthenticateUser;


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
		String uname = getServletContext().getInitParameter("Username");
		String pword = getServletContext().getInitParameter("Password");
		String ptype = getServletContext().getInitParameter("PassengerType");
				
		AuthenticateUser au = new AuthenticateUser(uname, pword);
		// validate login
		if(au.Authenticate(UserName, PassWord)){
			// if valid login
			// start session
			HttpSession session = request.getSession();
			String username = (String)session.getAttribute("username");
			String passengerType = (String)session.getAttribute("passengerType");
			
			if(username == null && passengerType == null){
				username = UserName;
				session.setAttribute("username", username);
						
				passengerType = ptype;
				session.setAttribute("passengerType", passengerType);
				System.out.println("Session start: " + username + ", " + passengerType);
				System.out.println();
			}
					
			if(username != null || passengerType != null){	
				// redirect to home page
				System.out.println("Current session state (UserAuthenticateServlet): " + username + ", " + passengerType);
				System.out.println("Redirecting to home.jsp (UserAuthenticateServlet)");
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
			request.getRequestDispatcher("Login").forward(request, response);
		}
				
	}

}
