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
	<div class="row row-cols-lg-auto form-holder">
		<div id="ticket-app" class="container container-lg form-main">
			<div class="row row-cols-lg-auto">
	        	<h1>Buy Ticket</h1>
			</div>
			<form method="post" action="Ticket">
				<div class="row row-cols-lg-auto">
					<div class="col-lg-5 d-flex align-content-center flex-wrap">
						<label class="form col-form-label">Starting Point: </label>
					</div>			
					<div class="col-lg-7 d-flex align-content-center flex-wrap">
	                    <select name='start' v-model="start" class="form form-select" @change="determineDisplay()">
	                    	<option value=''>Select Starting Point</option>
		                    <optgroup label="Quezon City Stations">
		                    	<option value='North-Avenue'>North Avenue</option>
		                    	<option value='Quezon-Ave'>Quezon Avenue</option>
								<option value='Kamuning'>Kamuning</option>
								<option value='Cubao'>Cubao</option>
								<option value='Santolan-Annapolis'>Santolan-Annapolis</option>
		                    </optgroup>
							<optgroup label="Pasig City Stations">
		                    	<option value='Ortigas'>Ortigas</option>
		                    </optgroup>
		                    <optgroup label="Mandaluyong City Stations">
		                    	<option value='Shaw-Boulevard'>Shaw Blvd.</option>
								<option value='Boni-Avenue'>Boni Avenue</option>
		                    </optgroup>
		                    <optgroup label="Makati City Stations">
		                    	<option value='Guadalupe'>Guadalupe</option>
								<option value='Buendia'>Buendia</option>
								<option value='Ayala'>Ayala</option>
								<option value='Magallanes'>Magallanes</option>
		                    </optgroup>
		                    <optgroup label="Pasay City Stations">
		                    	<option value='Taft-Avenue'>Taft Avenue</option>
		                    </optgroup>
						</select>
					</div>
				</div>
				<div class="row row-cols-lg-auto">
					<div class="col-lg-12 d-flex align-content-center flex-wrap">
	                    <label><input type="radio" name="direction" v-model="bound" value="northbound" @change="determineDisplay()"> North bound</label>
					</div>		
				</div>
				<div class="row row-cols-lg-auto">
					<div class="col-lg-12 d-flex align-content-center flex-wrap">
						<label><input type="radio" name="direction" v-model="bound" value="southbound"  @change="determineDisplay()"> South bound</label>
					</div>
				</div>
				<div class="row row-cols-lg-auto" title="Select Start Point and Bound to enable Select Destination box">
					<div class="col-lg-5 d-flex align-content-center flex-wrap">
						<label class="form col-form-label">End Destination: </label>
					</div>			
					<div class="col-lg-7 d-flex align-content-center flex-wrap">
	                    <select :disabled="isDisabledEnd" name='end' v-model="end" class="form form-select" >
	                    <option value=''>Select Destination</option>
		                    <optgroup v-if="displayQuezon" label="Quezon City Stations">
		                    	<option v-if="stations[0].display" value='North-Avenue'>{{stations[0].label}}</option>
		                    	<option v-if="stations[1].display" value='Quezon-Ave'>{{stations[1].label}}</option>
								<option v-if="stations[2].display" value='Kamuning'>{{stations[2].label}}</option>
								<option v-if="stations[3].display" value='Cubao'>{{stations[3].label}}</option>
								<option v-if="stations[4].display" value='Santolan-Annapolis'>{{stations[4].label}}</option>
		                    </optgroup>
							<optgroup v-if="displayPasig" label="Pasig City Stations">
		                    	<option v-if="stations[5].display"value='Ortigas'>{{stations[5].label}}</option>
		                    </optgroup>
		                    <optgroup v-if="displayMandaluyong" label="Mandaluyong City Stations">
		                    	<option v-if="stations[6].display" value='Shaw-Boulevard'>{{stations[6].label}}</option>
								<option v-if="stations[7].display" value='Boni-Avenue'>{{stations[7].label}}</option>
		                    </optgroup>
		                    <optgroup v-if="displayMakati" label="Makati City Stations">
		                    	<option v-if="stations[8].display" value='Guadalupe'>{{stations[8].label}}</option>
								<option v-if="stations[9].display" value='Buendia'>{{stations[9].label}}</option>
								<option v-if="stations[10].display" value='Ayala'>{{stations[10].label}}</option>
								<option v-if="stations[11].display" value='Magallanes'>{{stations[11].label}}</option>
		                    </optgroup>
		                    <optgroup v-if="displayPasay" label="Pasay City Stations">
		                    	<option v-if="stations[12].display" value='Taft-Avenue'>{{stations[12].label}}</option>
		                    </optgroup>
						</select>
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
                	start: "",
                	end: "",
                    bound: "",
                    displayQuezon: false,
                    displayPasig: false,
                    displayMandaluyong: false,
                    displayMakati: false,
                    displayPasay: false,
                    stations: [
                    	{id: 1, label: "North-Avenue", display: false, city: "Quezon-City"},
                    	{id: 2, label: "Quezon-Ave", display: false, city: "Quezon-City"},
                    	{id: 3, label: "Kamuning", display: false, city: "Quezon-City"},
                    	{id: 4, label: "Cubao", display: false, city: "Quezon-City"},
                    	{id: 5, label: "Santolan-Annapolis", display: false, city: "Quezon-City"},
                    	{id: 6, label: "Ortigas", display: false, city: "Pasig-City"},
                    	{id: 7, label: "Shaw-Boulevard", display: false, city: "Mandaluyong-City"},
                    	{id: 8, label: "Boni-Avenue", display: false, city: "Mandaluyong-City"},
                    	{id: 9, label: "Guadalupe", display: false, city: "Makati-City"},
                    	{id: 10, label: "Buendia", display: false, city: "Makati-City"},
                    	{id: 11, label: "Ayala", display: false, city: "Makati-City"},
                    	{id: 12, label: "Magallanes", display: false, city: "Makati-City"},
                    	{id: 13, label: "Taft-Avenue", display: false, city: "Pasay-City"}
                    ]
                }
            },
            computed:{
            	isDisabledEnd(){
            		return this.start === "" || this.bound === ""
            	},
            	isDisabled(){
            		return this.start === "" || this.bound === "" || this.end === ""
            	}
            },
            methods:{
            	determineDisplay(){
            		this.displayQuezon = false
            		this.displayPasig = false;
            		this.displayMandaluyong = false;
            		this.displayMakati = false;
            		this.displayPasay = false;
                    Object.keys(this.stations).forEach(key => {
            			this.stations[key].display = false;
            		}) 
                	if(this.bound === "northbound"){
                		var i = this.stations.length - 1;
                		for( ; i >= 0; i--){
                			if(this.stations[i].label === this.start){
                				i--;
                				for( ; i >= 0; i--){
                					this.stations[i].display = true;
                        		}
                			}
                		}
                	}
                	else if(this.bound === "southbound"){
                		var i = 0;
                		for( ; i < this.stations.length; i++){
                			if(this.stations[i].label === this.start){
                				i++;
                				for( ; i < this.stations.length; i++ ){
                					this.stations[i].display = true;
                				}
                			}
                		}
                	}
                	Object.keys(this.stations).forEach(key => {
                		if(this.stations[key].display && this.stations[key].city === "Quezon-City"){
            				this.displayQuezon = true;
            			}
            			if(this.stations[key].display && this.stations[key].city === "Pasig-City"){
            				this.displayPasig = true;
            			}
            			if(this.stations[key].display && this.stations[key].city === "Mandaluyong-City"){
            				this.displayMandaluyong = true;
            			}
            			if(this.stations[key].display && this.stations[key].city === "Makati-City"){
            				this.displayMakati = true;
            			}
            			if(this.stations[key].display && this.stations[key].city === "Pasay-City"){
            				this.displayPasay = true;
            			}
                	})
                }
            }
        })
        .mount('#ticket-app')
    </script>
</body>
</html>