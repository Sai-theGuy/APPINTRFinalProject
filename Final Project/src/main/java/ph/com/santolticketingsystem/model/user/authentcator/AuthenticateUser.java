package ph.com.santolticketingsystem.model.user.authentcator;

public class AuthenticateUser {
	
	public AuthenticateUser(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	String username = "";
	String password = "";
	
	public Boolean Authenticate(String UserName, String PassWord){
		Boolean result = false;
		if(username.equals(UserName) && password.equals(PassWord)){
			result = true;
		}
		return result;
	}
}
