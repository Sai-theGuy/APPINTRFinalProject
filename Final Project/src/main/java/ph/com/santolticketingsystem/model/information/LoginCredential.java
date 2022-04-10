package ph.com.santolticketingsystem.model.information;

public class LoginCredential {
	public LoginCredential(String UserName, String Password)
	{
		this.UserName = UserName;
		this.Password = Password;
	}
	
	private String UserName;
	private String Password;
	
	public String getUserName() {
		return UserName;
	}
	
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public void setPassword(String password) {
		Password = password;
	}

	

}
