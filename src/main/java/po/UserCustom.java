package po;

public class UserCustom extends User{
	String token;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserCustom(){}
	public UserCustom(Integer userId){
		this.setUserId(userId);
	}

	
}
