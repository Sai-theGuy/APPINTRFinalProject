package ph.com.santolticketingsystem.model.information;

public class UserInformation {
	
	public UserInformation(String FirstName, String LastName, String Username , String Password, String PassengerType)
	{
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Username = Username;
		this.Password = Password;
		this.PassengerType = PassengerType;
	}
	
	private String FirstName;
	private String LastName;
	private String Username;
	private String Password;
	private String PassengerType;
	
	public String getFirstName() {
		return FirstName;
	}
	
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	
	public String getLastName() {
		return LastName;
	}
	
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	public String getUsername() {
		return Username;
	}
	
	public void setUsername(String username) {
		Username = username;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public void setPassword(String password) {
		Password = password;
	}
	
	public String getPassengerType() {
		return PassengerType;
	}
	
	public void setPassengerType(String passengerType) {
		PassengerType = passengerType;
	}
}
