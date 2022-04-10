<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error-page-generic.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="icon" href="https://www.pikpng.com/pngl/m/11-117927_black-train-icon-train-vector-icon-png-clipart.png" type = "image/x-icon">
	<% if(session.getAttribute("username") != null)
	{ 
		request.getRequestDispatcher("Home").forward(request, response);
	} %>
</head>
<body>
	<%@ include file="partial/_navbar.jsp"%>
	<div class="row row-cols-lg-auto form-holder">
		<div id="login-app" class="container container-lg form-main">
			<div v-if="login">
			<%
				if (session.getAttribute("successMessage") != null) {
			%>
				<div class="alert alert-success" role="alert">
					<%=session.getAttribute("successMessage").toString()%>
				</div>
				<%
					session.removeAttribute("successMessage");
				}
				%>
				<%
				if (request.getAttribute("errorMessage") != null) {
				%>
					<div class="alert alert-danger" role="alert">
						<%=request.getAttribute("errorMessage").toString()%>
					</div>
				<%
					request.removeAttribute("errorMessage");
				}
			%>
				<div class="row row-cols-lg-auto">
	        		<h1>Login</h1>
				</div>
				<form method="post" action="Home">
					<div class="row row-cols-lg-auto">
						<div class="col-lg-5 d-flex align-content-center flex-wrap">
							<label class="form col-form-label">Username: </label>
						</div>			
						<div class="col-lg-7 d-flex align-content-center flex-wrap">
		                    <input type="text" class="form form-control" name="UserName" placeholder="Enter Username" v-model="uname" @change="isDisabled()">
						</div>
					</div>
					<div class="row row-cols-lg-auto" title="Select Start Point and Bound to enable Select Destination box">
						<div class="col-lg-5 d-flex align-content-center flex-wrap">
							<label class="form col-form-label">Password: </label>
						</div>			
						<div class="col-lg-7 d-flex align-content-center flex-wrap">
		                    <input type="password" class="form form-control" name="PassWord" placeholder="Enter Password" v-model="pword" @change="isDisabled()">
						</div>
					</div>
					<div class="row row-cols-lg-auto" title="Select Start Point and Bound to enable Select Destination box">
						<div class="col-lg-6 ml-auto p-2 d-flex justify-content-start">
		                	<button class="btn btn-primary" type="button" @click="loggingIn(false)">Register</button>
		                </div>
						<div class="col-lg-6 ml-auto p-2 d-flex justify-content-end">
		                	<input :disabled="isLogDisabled" type="submit" value="Buy Ticket" class="btn btn-dark">
		                </div>
					</div>
				</form>
			</div>
			<div v-else>
				<div class="row row-cols-lg-auto">
		        	<h1>Register</h1>
				</div>
				<div v-if="errorTriggered" class="alert alert-danger">
					{{msg}}
				</div>
				<form action="Register" method="post">
					<div class="row row-cols-lg-auto">
						<div class="col-lg-4 d-flex align-content-center flex-wrap">
							<label for="firstname" class="form col-form-label">First Name: </label>
						</div>			
						<div class="col-lg-8 d-flex align-content-center flex-wrap">
		                    <input type="text" id="firstname" class="form-control" name="firstname" 
		                    	v-model="fname"
		                    	@change="regFname()"
		                    >
						</div>
					</div>
					<div class="row row-cols-lg-auto">
						<div class="col-lg-4 d-flex align-content-center flex-wrap">
							<label for="lastname" class="form col-form-label">Last Name: </label>
						</div>			
						<div class="col-lg-8 d-flex align-content-center flex-wrap">
		                    <input type="text" id="lastname" class="form-control" name="lastname" 
		                    	v-model="lname"
		                    	@change="regLname()"
		                    >
						</div>
					</div>
					<div class="row row-cols-lg-auto">
						<div class="col-lg-12 d-flex align-content-center flex-wrap">
							<label class="form col-form-label">Passenger Type: </label>
						</div>			
					</div>
					<div class="row row-cols-lg-auto">
						<div class="col-lg-1 d-flex align-content-center flex-wrap">
		                    <div class="form-check">
								<input id="regular" class="form-check-input" type="radio" name="role" value="Regular" 
									v-model="PType"
								> 
							</div>
						</div>
						<div class="col-lg-11 d-flex align-content-center flex-wrap">
							<div class="form-check-label">
								<label for="regular"> Regular </label>
							</div>	
						</div>
					</div>
					<div class="row row-cols-lg-auto">
						<div class="col-lg-1 d-flex align-content-center flex-wrap">
		                    <div class="form-check">
								<input id="student" class="form-check-input" type="radio" name="role" value="Student" 
									v-model="PType"
								> 
							</div>
						</div>
						<div class="col-lg-11 d-flex align-content-center flex-wrap">
							<div class="form-check-label">
								<label for="student"> Student </label>
							</div>	
						</div>
					</div>
					<div class="row row-cols-lg-auto">
						<div class="col-lg-1 d-flex align-content-center flex-wrap">
		                    <div class="form-check">
								<input id="senior" class="form-check-input" type="radio" name="role" value="Senior" 
									v-model="PType"
								> 
							</div>
						</div>
						<div class="col-lg-11 d-flex align-content-center flex-wrap">
							<div class="form-check-label">
								<label for="senior"> Senior </label>
							</div>	
						</div>
					</div>
					<div class="row row-cols-lg-auto">
						<div class="col-lg-1 d-flex align-content-center flex-wrap">
		                    <div class="form-check">
								<input id="pwd" class="form-check-input" type="radio" name="role" value="PWD" 
									v-model="PType"
								>
							</div>
						</div>
						<div class="col-lg-11 d-flex align-content-center flex-wrap">
							<div class="form-check-label">
								<label for="pwd"> With Disabilities </label>
							</div>	
						</div>
					</div>
					<div class="row row-cols-lg-auto">
						<div class="col-lg-4 d-flex align-content-center flex-wrap">
							<label for="regUsername" class="form col-form-label">Username: </label>
						</div>			
						<div class="col-lg-8 d-flex align-content-center flex-wrap">
		                    <input type="text" id="regUsername" class="form-control" name="regUsername" 
		                    	v-model="Uname"
		                    	@change="regUname()"
		                    >
						</div>
					</div>
					<div class="row row-cols-lg-auto">
						<div class="col-lg-4 d-flex align-content-center flex-wrap">
							<label for="regPassword" class="form col-form-label">Password: </label>
						</div>			
						<div class="col-lg-8 d-flex align-content-center flex-wrap">
							<input type="password" id="regPassword" class="form-control" name="regPassword" 
								v-model="Pword" 
								@change="regPword()"
							>
						</div>
					</div>
					<div class="row row-cols-lg-auto">
						<div class="col-lg-4 d-flex align-content-center flex-wrap">
							<label for="regConPassword" class="form col-form-label">Confirm Password: </label>
						</div>			
						<div class="col-lg-8 d-flex align-content-center flex-wrap">
							<input type="password" id="regConPassword" class="form-control" name="regConPassword" 
							 	v-model="ConPword"
							 	@change="pwordConfirm();"
							 >
						</div>
					</div>
					<div class="row row-cols-lg-auto">
						<div class="col-lg-6 ml-auto p-2 d-flex justify-content-start">
		                	<button class="btn btn-primary" type="button" @click="loggingIn(true)">Login</button>
		                </div>
						<div class="col-lg-6 ml-auto p-2 d-flex justify-content-end">
		                	<input :disabled="isRegDisabled||errorTriggered" type="submit" class="btn btn-dark" value="Register" />
		                </div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="https://unpkg.com/vue@3"></script>
    <script>
        const bpApp = Vue.createApp({
            data(){
                return{
                	uname: "",
                	pword: "",
                	login: true,
                	fname: "",
                	lname: "",
                	PType: "",
                	Uname: "",
                	Pword: "",
                	ConPword: "",
                	errorTriggered: false,
					msg: ""                	
                }
            },
            computed:{
            	isLogDisabled(){
            		return this.uname === "" || this.pword === ""
            	},
            	isRegDisabled(){
            		return this.fname === "" 
            		|| this.lname === ""
            		|| this.PType === ""
            		|| this.Uname === ""
            		|| this.Pword === ""
            		|| this.ConPword === ""
            	}
            },
            methods:{
            	loggingIn(login){
					this.login = login            		
            	},
            	pwordConfirm(){
            		if(this.Pword != this.ConPword){
            			this.msg = "Password and Confirm Password does not match. Please make sure it matches.";
            			this.errorTriggered = true;
            		}
            		else{
            			this.msg = "";
            			this.errorTriggered = false;
            		}
            	},
            	regFname(){
            		if(this.fname === ""){
            			this.msg = "First Name cannot be blank.";
            			this.errorTriggered = true;
            		}
            		else{
            			this.msg = "";
            			this.errorTriggered = false;
            		}
            	},
            	regLname(){
            		if(this.lname === ""){
            			this.msg = "Last Name cannot be blank.";
            			this.errorTriggered = true;
            		}
            		else{
            			this.msg = "";
            			this.errorTriggered = false;
            		}
            	},
            	regUname(){
            		if(this.Uname === ""){
            			this.msg = "Username cannot be blank.";
            			this.errorTriggered = true;
            		}
            		else{
            			this.msg = "";
            			this.errorTriggered = false;
            		}
            	},
            	regPword(){
            		if(this.Pword === ""){
            			this.msg = "Password cannot be blank.";
            			this.errorTriggered = true;
            		}
            		else{
            			this.msg = "";
            			this.errorTriggered = false;
            		}
            	},
            	regConPword(){
            		if(this.ConPword === ""){
            			this.msg = "Confirm Password cannot be blank.";
            			this.errorTriggered = true;
            		}
            		else{
            			this.msg = "";
            			this.errorTriggered = false;
            		}
            	}
            }
        })
        .mount('#login-app')
    </script>
    <script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous">
	</script>
<%@ include file="partial/_footer.jsp"%>
</body>
</html>