<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="icon" href="https://www.pikpng.com/pngl/m/11-117927_black-train-icon-train-vector-icon-png-clipart.png" type = "image/x-icon">
</head>
<body>
<%@ include file="/partial/_navbar.jsp" %> 
<div class="container form-holder">
	<div class="alert alert-danger" role="alert">
		<h2>Error Encountered</h2>
		<p>Sorry for the inconvenience. An error has been encountered while processing your request. 
		<br/>Please try again later. Thank you!</p>
	</div>
<a href="Home">Redirect to Home</a>
</div>
<%@ include file="/partial/_footer.jsp" %> 
</body>
</html>