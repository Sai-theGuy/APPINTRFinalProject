<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<meta charset="ISO-8859-1">
	<link rel="icon" href="https://www.pikpng.com/pngl/m/11-117927_black-train-icon-train-vector-icon-png-clipart.png" type = "image/x-icon">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>            
	<title>SanTol Ticketing System</title>
<body>
	<div class="row row-cols-lg-auto form-holder">
		<div id="login-app" class="container container-lg form-main">
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
				<div class="col-lg-12 ml-auto p-2 d-flex justify-content-end">
                	<input :disabled="isDisabled" type="submit" value="Buy Ticket" class="btn btn-dark">
                </div>
			</form>
		</div>
	</div>
	<script src="https://unpkg.com/vue@3"></script>
    <script>
        const bpApp = Vue.createApp({
            data(){
                return{
                	uname: "",
                	pword: ""
                }
            },
            computed:{
            	isDisabled(){
            		return this.uname === "" || this.pword === ""
            	}
            }
        })
        .mount('#login-app')
    </script>
	<a href="Home">clikc here</a>
</body>
</html>