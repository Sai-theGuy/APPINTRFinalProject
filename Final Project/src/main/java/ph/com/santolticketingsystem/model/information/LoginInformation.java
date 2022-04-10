package ph.com.santolticketingsystem.model.information;

public class LoginInformation {
	public LoginInformation(String UserName, String Password, String PassengerType)
	{
		this.UserName = UserName;
		this.Password = Password;
		this.PassengerType = PassengerType;
	}
	
	public LoginInformation() {
	}

	private String UserName;
	private String PassengerType;
	private String Password;
	
	public String getUserName() {
		return UserName;
	}
	
	public void setUserName(String userName) {
		UserName = userName;
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
