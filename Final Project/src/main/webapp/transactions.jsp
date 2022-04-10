<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transactions</title>
<%@ page import="ph.com.santolticketingsystem.model.user.transaction.UserTransaction" %>
<%@ page import="ph.com.santolticketingsystem.model.user.transaction.UserTransactions" %>
<%@ page import="java.util.Iterator" %>
</head>
<body>
<%@ include file="partial/_navbar.jsp"%>
	<div class="container" style="width: 100% !important;">
		<div class="row row-cols-lg-auto">
			<div class="col-lg-1 align-content-center back-div">
				<a href="Home">
                	<i class="fa fa-angle-left fa-2x" aria-hidden="true"><span class="back">Back</span></i>
                </a>
			</div>
		</div>
		<div class="row row-cols-lg-auto">
			<div class="col-lg-5 d-flex flex-column align-content-center" style="width: 100% !important;">
				<%
				Double totalPrice = 0.00;
				Iterator<UserTransaction> transactions = (Iterator)request.getAttribute("transactions");
				if(transactions == null){
				%>
				<div class="table table-light">
					<table class="table">
						<tr>
							<th>Transaction ID<th>
							<th>Username<th>
							<th>Date Time<th>
							<th>Start<th>
							<th>Stop<th>
							<th>Price<th>
						</tr>
						<%
								while(transactions.hasNext()){
								UserTransaction transaction = (UserTransaction) transactions.next();
								
								String TransactionID = String.valueOf(transaction.getTransactionID());
								String Username = String.valueOf(transaction.getUsername());
								String DateTime = String.valueOf(transaction.getDateTime());
								String Start = String.valueOf(transaction.getStart());
								String Stop = String.valueOf(transaction.getDestination());
								String Price = String.valueOf(transaction.getPrice());
								
								Double subTotal = transaction.getPrice();
						%>
						<tr>
							<td><%= TransactionID %><td>
							<td><%= Username %><td>
							<td><%= DateTime %><td>
							<td><%= Start %><td>
							<td><%= Stop %><td>
							<td><%= Price %><td>
						</tr>
						<%
								totalPrice += subTotal;
						}
						%>
						<tr>
							<td><%= Double.toString(totalPrice) %> <td>
						</tr>
						<%} 
						else{ %>
							<div class="alert alert-danger" role="alert">
								<h2>No Transactions</h2>
								<p>Sorry, but it seems like you have no records of ticket buying transactions yet. Once you buy a ticket it will be shown here.</p>
							</div>
						<%
						}
						%>
					</table>
				</div>
			</div>
		</div>
	</div>
<%@ include file="partial/_footer.jsp"%>
</body>
</html>