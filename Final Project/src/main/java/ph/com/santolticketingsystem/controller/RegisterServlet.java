package ph.com.santolticketingsystem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ph.com.santolticketingsystem.model.information.LoginInformation;
import ph.com.santolticketingsystem.model.information.UserInformation;
//import com.benilde.finalproject.Model.UserBean;
import ph.com.santolticketingsystem.utility.singleton.SingletonDB;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String firstname;
	String lastname;
	String passengerType;
	String username;
	String password;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.firstname = request.getParameter("firstname");
		this.lastname = request.getParameter("lastname");
		this.passengerType = request.getParameter("role");
		this.username = request.getParameter("regUsername");
		this.password = request.getParameter("regPassword");
		System.out.println(this.firstname + " " + this.lastname + " " + this.passengerType + " " + this.username + " "  + this.password + " ");
		
		
		SingletonDB.createUsersTable(
			getServletContext().getInitParameter("jdbcDriver")
			, getServletContext().getInitParameter("jdbcUrl")
			, getServletContext().getInitParameter("dbUserName")
			, getServletContext().getInitParameter("dbUserPassword")
		);
		
		SingletonDB.createLoginsTable(
			getServletContext().getInitParameter("jdbcDriver")
			, getServletContext().getInitParameter("jdbcUrl")
			, getServletContext().getInitParameter("dbUserName")
			, getServletContext().getInitParameter("dbUserPassword")
		);

		UserInformation ui = new UserInformation(firstname, lastname, username, password, passengerType);
		System.out.println(ui.getFirstName() + " " + ui.getLastName() + " " + ui.getUsername() + " " + ui.getPassword() + " " + ui.getPassengerType());
		System.out.println();
		
		SingletonDB.insertUser(
			getServletContext().getInitParameter("jdbcDriver")
			, getServletContext().getInitParameter("jdbcUrl")
			, getServletContext().getInitParameter("dbUserName")
			, getServletContext().getInitParameter("dbUserPassword")
			, ui
		);
		
		LoginInformation li = new LoginInformation(username, password, passengerType);
		System.out.println(li.getUserName() + " " + li.getPassword() + " " + li.getPassengerType());
		System.out.println();
		
		SingletonDB.insertLogin(
			getServletContext().getInitParameter("jdbcDriver")
			, getServletContext().getInitParameter("jdbcUrl")
			, getServletContext().getInitParameter("dbUserName")
			, getServletContext().getInitParameter("dbUserPassword")
			, li 
		);
		
		response.sendRedirect("Login");
	}
}
