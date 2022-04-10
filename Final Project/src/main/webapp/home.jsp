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
	<%@ include file="partial/_navbar.jsp"%>
	<div class="container form-holder">
		<form action="TicketMachine">
			<input type="submit" value="Buy Ticket">
		</form>
		
		<form action="Transactions">
			<input type="submit" value="Past Transactions">
		</form>
	</div>
	<%@ include file="partial/_footer.jsp"%>
</body>
</html>