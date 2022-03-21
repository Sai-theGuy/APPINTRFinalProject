package ph.com.santolticketingsystem.model.user.information;

public class UserInformation {
	
	UserInformation(String FirstName, String LastName, String PassengerType, String Password)
	{
		this.FirstName = FirstName;
	}
	
	private String FirstName;
	private String LastName;
	private String PassengerType;
	private String Password;
	
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
	public String getPassengerType() {
		return PassengerType;
	}
	public void setPassengerType(String passengerType) {
		PassengerType = passengerType;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
}
