<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="icon" href="https://www.pikpng.com/pngl/m/11-117927_black-train-icon-train-vector-icon-png-clipart.png" type = "image/x-icon">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>            
	<title>SanTol Ticketing System</title>
</head>
<body>
	<%@ include file="partial/_navbar.jsp"%>
	<div class="container">
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