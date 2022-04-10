<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error-page-generic.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="icon" href="https://www.pikpng.com/pngl/m/11-117927_black-train-icon-train-vector-icon-png-clipart.png" type = "image/x-icon">
	<% if(session.getAttribute("username") == null)
	{ 
		request.setAttribute("errorMessage", "Access Denied. Invalid Credentials.");
		request.getRequestDispatcher("Login").forward(request, response);
	} %>
</head>
<body>
	<%@ page import="ph.com.santolticketingsystem.model.user.transaction.UserTransaction" %>
	<%@ page import="ph.com.santolticketingsystem.model.user.transaction.UserTransactions" %>
	<%@ page import="java.util.Iterator" %>
	<%@ page import="java.text.DecimalFormat" %>
	<%@ include file="partial/_navbar.jsp" %>
	<div class="container" style="width: 100% !important;">
		<div class="row row-cols-lg-auto">
			<div class="col-lg-1 align-content-center back-div">
				<a href="Home">
                	<i class="fa fa-angle-left fa-2x" aria-hidden="true"><span class="back">Back</span></i>
                </a>
			</div>
		</div>
		<div class="row row-cols-lg-auto form-holder">
			<div class="col-lg-5 d-flex flex-column align-content-center" style="width: 100% !important;">
				<div>
				<%!
					DecimalFormat df = new DecimalFormat("#.##");
					Double totalPrice = 0.00;
				%>
				<%
					
					int length = (int)request.getAttribute("length");
					if(length > 0 && request.getAttribute("length") != null){
				%>
					<table class="table table-striped table-hover table-outline-dark">
						<thead>
							<tr>
								<th>Transaction ID</th>
								<th>Username</th>
								<th>Date Time</th>
								<th>Start</th>
								<th>Stop</th>
								<th>Price</th>
							</tr>
						</thead>	
						<%
							length--;
							while(length >= 0){
								String TransactionID = "TransactionID" + length;
								String Username = "Username" + length;
								String DateTime = "DateTime" + length;
								String Start = "Start" + length;
								String Stop = "Stop" + length;
								String Price = "Price" + length;
						%>
						<tr>
							<td><%= request.getAttribute(TransactionID) %></td>
							<td><%= request.getAttribute(Username) %></td>
							<td><%= request.getAttribute(DateTime) %></td>
							<td><%= request.getAttribute(Start) %></td>
							<td><%= request.getAttribute(Stop) %></td>
							<td><%= request.getAttribute(Price) %></td>
						</tr>
						<%	
								length--;
							}
						%>
						<%-- throw new Exception("Exception message"); --%>
						</table>
						<%
						} 
						else{ %>
							<div class="alert alert-danger" role="alert">
								<h2>No Transactions</h2>
								<p>Sorry, but it seems like you have no records of ticket buying transactions yet. Once you buy a ticket it will be shown here.</p>
							</div>
						<%
						}
						%>
				</div>
			</div>
		</div>
	</div>
<%@ include file="partial/_footer.jsp"%>
</body>
</html>